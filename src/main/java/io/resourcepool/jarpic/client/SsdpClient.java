package io.resourcepool.jarpic.client;

import io.resourcepool.jarpic.model.DiscoveryListener;
import io.resourcepool.jarpic.model.DiscoveryRequest;

/**
 * @author Loïc Ortola on 11/03/2016.
 */
public interface SsdpClient {

  /**
   * Discover specific devices of particular ServiceType.
   *
   * @param req      the discovery request
   * @param callback the discovery listener
   */
  void discoverServices(DiscoveryRequest req, DiscoveryListener callback);

  /**
   * Stop discovery.
   */
  void stopDiscovery();


}
