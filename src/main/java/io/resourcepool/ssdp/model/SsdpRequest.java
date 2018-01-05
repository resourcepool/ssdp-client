package io.resourcepool.ssdp.model;

/**
 * @author Loïc Ortola on 05/08/2017.
 */
public abstract class SsdpRequest {

  /**
   * @return a new Request Builder for a discovery SSDP request
   */
  public static DiscoveryRequest.Builder discover() {
    return DiscoveryRequest.builder();
  }

  /**
   * @return a new SSDP DiscoveryRequest for all device
   */
  public static DiscoveryRequest discoverAll() {
    return DiscoveryRequest.builder().build();
  }

  /**
   * @return a new SSDP DiscoveryRequest for all rootdevice
   */
  public static DiscoveryRequest discoverRootDevice() {
    return DiscoveryRequest.builder().serviceType("upnp:rootdevice").build();
  }

}
