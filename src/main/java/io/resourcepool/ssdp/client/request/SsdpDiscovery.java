package io.resourcepool.ssdp.client.request;

import io.resourcepool.ssdp.client.SsdpParams;

import java.net.DatagramPacket;

import static io.resourcepool.ssdp.client.SsdpParams.UTF_8;

/**
 * This creates the SSDP Discovery Request.
 *
 * @author Loïc Ortola on 05/08/2017
 */
public abstract class SsdpDiscovery {

  /**
   * Get Datagram from serviceType.
   *
   * @param serviceType the serviceType
   * @return the DatagramPacket matching the search request
   */
  public static DatagramPacket getDatagram(String serviceType) {
    StringBuilder sb = new StringBuilder("M-SEARCH * HTTP/1.1\r\n");
    sb.append("HOST: " + SsdpParams.getSsdpMulticastAddress().getHostAddress() + ":" + SsdpParams.getSsdpMulticastPort() + "\r\n");
    sb.append("MAN: \"ssdp:discover\"\r\n");
    sb.append("MX: 3\r\n");
    sb.append("USER-AGENT: Resourcepool SSDP Client\r\n");
    sb.append(serviceType == null ? "ST: ssdp:all\r\n" : "ST: " + serviceType + "\r\n\r\n");
    byte[] content = sb.toString().getBytes(UTF_8);
    return new DatagramPacket(content, content.length, SsdpParams.getSsdpMulticastAddress(), SsdpParams.getSsdpMulticastPort());
  }
}
