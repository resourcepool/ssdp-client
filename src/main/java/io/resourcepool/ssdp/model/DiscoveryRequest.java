package io.resourcepool.ssdp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents which service types are to be discovered using SSDP.
 *
 * @author Loïc Ortola on 05/08/2017
 */
public class DiscoveryRequest extends SsdpRequest {

  private List<String> serviceTypes;

  /**
   * @return the requested service types
   */
  public List<String> getServiceTypes() {
    return serviceTypes;
  }

  // BEGIN GENERATED CODE

  public static Builder builder() {
    return new Builder();
  }


  public static final class Builder {
    private Set<String> serviceTypes = new HashSet<String>();

    private Builder() {
    }

    public Builder serviceType(String serviceType) {
      this.serviceTypes.add(serviceType);
      return this;
    }

    public DiscoveryRequest build() {
      DiscoveryRequest req = new DiscoveryRequest();
      req.serviceTypes = new ArrayList<String>(serviceTypes);
      return req;
    }
  }

  // END GENERATED CODE
}
