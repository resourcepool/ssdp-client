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
  private static   int SSDP_MULTICAST_PORT = 1900;
  /**
   * separate the message sending port and receiving port
   * If another application on the same server also listens to udp port 1900, and the binding source port is 1900, the program may not receive a response
   */
  private static  int BIND_LOCAL_PORT=1900;

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
  public static void setSsdpMulticastPort(int port) {
      SSDP_MULTICAST_PORT=port;
  }

  public static int getBindLocalPort() {
    return BIND_LOCAL_PORT;
  }
  public static void setBindLocalPort(int port) {
    BIND_LOCAL_PORT=port;
  }
}
