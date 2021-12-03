package io.resourcepool.ssdp.client;

import io.resourcepool.ssdp.client.impl.SsdpClientImpl;
import io.resourcepool.ssdp.model.DiscoveryListener;
import io.resourcepool.ssdp.model.DiscoveryRequest;
import io.resourcepool.ssdp.model.SsdpClientOptions;

/**
 * @author Loïc Ortola on 11/03/2016.
 */
public abstract class SsdpClient {

  /**
   * Discover specific devices of particular ServiceType.
   *
   * @param req      the discovery request
   * @param options  the ssdp client options
   * @param callback the discovery listener
   */
  public abstract void discoverServices(DiscoveryRequest req, SsdpClientOptions options, DiscoveryListener callback);


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
  public static SsdpClient create() {
    return new SsdpClientImpl();
  }

}
