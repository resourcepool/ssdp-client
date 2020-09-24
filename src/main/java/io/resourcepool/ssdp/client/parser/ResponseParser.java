package io.resourcepool.ssdp.client.parser;

import io.resourcepool.ssdp.client.response.SsdpResponse;

import java.net.DatagramPacket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.resourcepool.ssdp.client.SsdpParams.UTF_8;

/**
 * @author Loïc Ortola on 11/03/2016.
 *         This class parses a DatagramPacket into a valid SsdpResponse
 */
public class ResponseParser {

  private static final Pattern CACHE_CONTROL_PATTERN = Pattern.compile("max-age[ ]*=[ ]*([0-9]+).*");
  // Date format for expires headers
  private static final SimpleDateFormat DATE_HEADER_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
  // Patterns
  private static final Pattern SEARCH_REQUEST_LINE_PATTERN = Pattern.compile("^HTTP/1\\.1 [0-9]+ .*");
  private static final Pattern SERVICE_ANNOUNCEMENT_LINE_PATTERN = Pattern.compile("NOTIFY \\* HTTP/1\\.1");
  private static final Pattern HEADER_PATTERN = Pattern.compile("(.*?):(.*)$");
  // CRLF
  private static final byte[] CRLF = "\r\n".getBytes(UTF_8);

  /**
   * Parse incoming Datagram into SsdpResponse.
   *
   * @param packet the incoming datagram.
   * @return the parsed SsdpResponse if it worked, null otherwise
   */
  public static SsdpResponse parse(DatagramPacket packet) {

    Map<String, String> headers = new HashMap<String, String>();
    byte[] body = null;
    SsdpResponse.Type type = null;

    byte[] data = packet.getData();
    // Find position of the last header data
    int endOfHeaders = findEndOfHeaders(data);
    if (endOfHeaders == -1) {
      endOfHeaders = packet.getLength();
    }
    // Retrieve all header lines
    String[] headerLines = new String(Arrays.copyOfRange(data, 0, endOfHeaders)).split("\r\n");

    // Determine type of message
    if (SEARCH_REQUEST_LINE_PATTERN.matcher(headerLines[0]).matches()) {
      type = SsdpResponse.Type.DISCOVERY_RESPONSE;
    } else if (SERVICE_ANNOUNCEMENT_LINE_PATTERN.matcher(headerLines[0]).matches()) {
      type = SsdpResponse.Type.PRESENCE_ANNOUNCEMENT;
    }

    // If type was not found, this is not a valid SSDP message. get out of here
    if (type == null) {
      return null;
    }

    // Let's parse our headers.
    for (int i = 1; i < headerLines.length; i++) {
      String line = headerLines[i];
      Matcher matcher = HEADER_PATTERN.matcher(line);
      if (matcher.matches()) {
        headers.put(matcher.group(1).toUpperCase().trim(), matcher.group(2).trim());
      }
    }
    // Determine expiry depending on the presence of cache-control or expires headers.
    long expiry = parseCacheHeader(headers);

    // Let's see if we have a body. If we do, let's copy the byte array and put it into the response for the user to get.
    int endOfBody = packet.getLength();
    if (endOfBody > endOfHeaders + 4) {
      body = Arrays.copyOfRange(data, endOfHeaders + 4, endOfBody);
    }

    return new SsdpResponse(type, headers, body, expiry, packet.getAddress());
  }

  /**
   * Parse both Cache-Control and Expires headers to determine if there is any caching strategy requested by service.
   *
   * @param headers the headers.
   * @return 0 if no strategy, or the timestamp matching the future expiration in milliseconds otherwise.
   */
  private static long parseCacheHeader(Map<String, String> headers) {
    if (headers.get("CACHE-CONTROL") != null) {
      String cacheControlHeader = headers.get("CACHE-CONTROL");
      Matcher m = CACHE_CONTROL_PATTERN.matcher(cacheControlHeader);
      if (m.matches()) {
        return new Date().getTime() + Long.parseLong(m.group(1)) * 1000L;
      }
    }
    if (headers.get("EXPIRES") != null) {
      try {
        return DATE_HEADER_FORMAT.parse(headers.get("EXPIRES")).getTime();
      } catch (ParseException e) {
      }
    }
    // No result, no expiry strategy
    return 0;
  }

  /**
   * Find the index matching the end of the header data.
   *
   * @param data the request data
   * @return the index if found, -1 otherwise
   */
  private static int findEndOfHeaders(byte[] data) {
    for (int i = 0; i < data.length - 3; i++) {
      if (data[i] != CRLF[0] || data[i + 1] != CRLF[1] || data[i + 2] != CRLF[0] || data[i + 3] != CRLF[1]) {
        continue;
      }
      // Headers finish here
      return i;
    }
    return -1;
  }
}
