package io.resourcepool.ssdp.client.util;

import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Utils used by the SsdpClient.
 *
 * @author Loïc Ortola on 05/08/2017
 */
public abstract class Utils {

  /**
   * Creates a list of viable network interfaces for Multicast.
   *
   * @throws SocketException if something bad happens
   */
  public static List<NetworkInterface> getMulticastInterfaces() throws SocketException {
    List<NetworkInterface> viableInterfaces = new ArrayList<NetworkInterface>();
    Enumeration e = NetworkInterface.getNetworkInterfaces();
    while (e.hasMoreElements()) {
      NetworkInterface n = (NetworkInterface) e.nextElement();
      Enumeration ee = n.getInetAddresses();
      while (ee.hasMoreElements()) {
        InetAddress i = (InetAddress) ee.nextElement();
        if (i.isSiteLocalAddress() && !i.isAnyLocalAddress() && !i.isLinkLocalAddress()
            && !i.isLoopbackAddress() && !i.isMulticastAddress()) {
          viableInterfaces.add(NetworkInterface.getByName(n.getName()));
        }
      }
    }
    return viableInterfaces;
  }
}
