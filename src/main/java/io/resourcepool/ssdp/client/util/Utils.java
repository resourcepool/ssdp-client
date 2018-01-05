package io.resourcepool.ssdp.client.util;

import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Utils used by the SsdpClient.
 *
 * @author Loïc Ortola on 05/08/2017
 */
public abstract class Utils {

  /**
   * Selects the appropriate interface to use Multicast.
   * This prevents computers with multiple interfaces to select the wrong one by default.
   *
   * @param socket the socket on which we want to bind the specific interface.
   * @throws SocketException if something bad happens
   */
  public static void selectAppropriateInterface(MulticastSocket socket) throws SocketException {
    Enumeration e = NetworkInterface.getNetworkInterfaces();
    while (e.hasMoreElements()) {
      NetworkInterface n = (NetworkInterface) e.nextElement();
      Enumeration ee = n.getInetAddresses();
      while (ee.hasMoreElements()) {
        InetAddress i = (InetAddress) ee.nextElement();
        if (i.isSiteLocalAddress() && !i.isAnyLocalAddress() && !i.isLinkLocalAddress()
            && !i.isLoopbackAddress() && !i.isMulticastAddress()) {
          socket.setNetworkInterface(NetworkInterface.getByName(n.getName()));
        }
      }
    }
  }
}
