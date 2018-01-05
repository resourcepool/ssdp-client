package io.resourcepool.ssdp.model;


/**
 * This class handles all actions related to the Service Discovery of previously specified DiscoveryRequest.
 *
 * @author Loïc Ortola on 05/08/2017
 */
public interface DiscoveryListener {
  /**
   * Called when new service has been found on the network.
   *
   * @param service the service that has been found
   */
  void onServiceDiscovered(SsdpService service);

  /**
   * Called when a known service announces itself in some way.
   * This can either mean it is alive or that it is going offline (byebye).
   *
   * @param announcement the announcement of the specific service
   */
  void onServiceAnnouncement(SsdpServiceAnnouncement announcement);

  /**
   * Called when an exception occured within the process.
   *
   * @param ex the exception raised
   */
  void onFailed(Exception ex);
}
