package io.resourcepool.jarpic.client;

import io.resourcepool.jarpic.model.DiscoveryListener;
import io.resourcepool.jarpic.model.DiscoveryRequest;

/**
 * @author Loïc Ortola on 11/03/2016.
 */
public abstract class SsdpClient {

  /**
   * Discover specific devices of particular ServiceType.
   *
   * @param req      the discovery request
   * @param callback the discovery listener
   */
  public abstract void discoverServices(DiscoveryRequest req, DiscoveryListener callback);

  /**
   * Stop discovery.
   */
  public abstract void stopDiscovery();

  /**
   * @return new instance of SsdpClient.
   */
  public SsdpClient create() {
    return new SsdpClientImpl();
  }


}
