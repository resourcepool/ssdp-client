package io.resourcepool.ssdp.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

/**
 * This holds the parameters of SSDP protocol (multicast ip and port).
 *
 * @author Loïc Ortola on 05/08/2017
 */
public class SsdpParams {
  private static InetAddress ssdpMulticastIpv4Address;
  private static final int SSDP_MULTICAST_PORT = 1900;

  public static final Charset UTF_8 = Charset.forName("UTF-8");

  /**
   * @return the Ssdp Multicast Ip Address
   */
  public static InetAddress getSsdpMulticastAddress() {
    if (ssdpMulticastIpv4Address == null) {
      synchronized (SsdpParams.class) {
        if (ssdpMulticastIpv4Address == null) {
          try {
            ssdpMulticastIpv4Address = InetAddress.getByName("239.255.255.250");
          } catch (UnknownHostException e) {
            throw new IllegalStateException(e);
          }
        }
      }
    }
    return ssdpMulticastIpv4Address;
  }

  /**
   * @return the Ssdp Port
   */
  public static int getSsdpMulticastPort() {
    return SSDP_MULTICAST_PORT;
  }
}
