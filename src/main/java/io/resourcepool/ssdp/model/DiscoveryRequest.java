package io.resourcepool.ssdp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.resourcepool.ssdp.client.impl.SsdpClientImpl.DEFAULT_INTERVAL_BETWEEN_REQUESTS;

/**
 * This class represents which service types are to be discovered using SSDP.
 *
 * @author Loïc Ortola on 05/08/2017
 */
public class DiscoveryRequest extends SsdpRequest {

  private List<String> serviceTypes;
  private Long intervalBetweenRequests;

  /**
   * @return the requested service types
   */
  public List<String> getServiceTypes() {
    return serviceTypes;
  }

  public Long getIntervalBetweenRequests() {
    return intervalBetweenRequests;
  }

  // BEGIN GENERATED CODE

  public static Builder builder() {
    return new Builder();
  }


  public static final class Builder {
    private Set<String> serviceTypes = new HashSet<String>();
    private Long intervalBetweenRequests = DEFAULT_INTERVAL_BETWEEN_REQUESTS;
    private Builder() {
    }

    public Builder serviceType(String serviceType) {
      this.serviceTypes.add(serviceType);
      return this;
    }

    /**
     * Interval between requests in milliseconds.
     * Defaults to 10 000 ms
     * @param intervalBetweenRequests the interval between requests in ms
     * @return the builder
     */
    public Builder intervalBetweenRequests(Long intervalBetweenRequests) {
      if (intervalBetweenRequests < 10) {
        throw new IllegalArgumentException("Interval between requests must be at least 10 milliseconds.");
      }
      this.intervalBetweenRequests = intervalBetweenRequests;
      return this;
    }


    public DiscoveryRequest build() {
      DiscoveryRequest req = new DiscoveryRequest();
      req.serviceTypes = new ArrayList<String>(serviceTypes);
      req.intervalBetweenRequests = intervalBetweenRequests;
      return req;
    }
  }

  // END GENERATED CODE
}
