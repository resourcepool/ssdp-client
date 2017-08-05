package io.resourcepool.jarpic.model;

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

  // END GENERATED CODE
}
