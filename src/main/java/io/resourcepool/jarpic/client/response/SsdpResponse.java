package io.resourcepool.jarpic.client.response;

import io.resourcepool.jarpic.model.SsdpService;
import io.resourcepool.jarpic.model.SsdpServiceAnnouncement;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a raw SsdpResponse.
 *
 * @author Loïc Ortola on 05/08/2017
 */
public class SsdpResponse {
  public Type getType() {
    return type;
  }

  public enum Type {
    DISCOVERY_RESPONSE, PRESENCE_ANNOUNCEMENT
  }

  private final Map<String, String> headers;
  private final byte[] body;
  private final InetAddress originAddress;
  private final long expiry;
  private final Type type;

  /**
   * Constructor.
   *
   * @param type          the response type
   * @param headers       the response headers
   * @param body          the response body or null if none
   * @param expiry        the expiration or 0 if none
   * @param originAddress the origin ip address
   */
  public SsdpResponse(Type type, Map<String, String> headers, byte[] body, long expiry, InetAddress originAddress) {
    this.type = type;
    this.headers = headers;
    this.body = body;
    this.expiry = expiry;
    this.originAddress = originAddress;
  }

  // BEGIN GENERATED CODE

  public byte[] getBody() {
    return body;
  }

  public Map<String, String> getHeaders() {
    return new HashMap<String, String>(headers);
  }

  public InetAddress getOriginAddress() {
    return originAddress;
  }

  public SsdpService toService() {
    return new SsdpService(this);
  }

  public SsdpServiceAnnouncement toServiceAnnouncement() {
    return new SsdpServiceAnnouncement(this);
  }

  public long getExpiry() {
    return expiry;
  }

  public boolean isExpired() {
    return expiry <= 0 || new Date().getTime() > expiry;
  }

  @Override
  public String toString() {
    return "SsdpResponse{" +
        ", headers=" + headers +
        ", body=" + Arrays.toString(body) +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SsdpResponse that = (SsdpResponse) o;

    if (!headers.equals(that.headers)) return false;
    return Arrays.equals(body, that.body);
  }

  @Override
  public int hashCode() {
    int result = headers.hashCode();
    result = 31 * result + Arrays.hashCode(body);
    return result;
  }

  // END GENERATED CODE

}
