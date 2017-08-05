package io.resourcepool.jarpic.model;

import io.resourcepool.jarpic.client.response.SsdpResponse;

import java.net.InetAddress;
import java.util.Map;

/**
 * This represents a SSDP Service.
 *
 * @author Loïc Ortola on 05/08/2017
 */
public class SsdpService {
  private String serialNumber;
  private String serviceType;
  private String location;
  private final InetAddress remoteIp;
  private final SsdpResponse originalResponse;

  /**
   * @param response the raw SsdpResponse
   */
  public SsdpService(SsdpResponse response) {
    Map<String, String> headers = response.getHeaders();
    this.serialNumber = headers.get("USN");
    this.serviceType = headers.get("ST");
    this.location = headers.get("LOCATION");
    if (this.location == null) {
      this.location = headers.get("AL");
    }
    this.remoteIp = response.getOriginAddress();
    this.originalResponse = response;
  }

  // BEGIN GENERATED CODE

  public String getServiceType() {
    return serviceType;
  }

  public InetAddress getRemoteIp() {
    return remoteIp;
  }

  public String getLocation() {
    return location;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public SsdpResponse getOriginalResponse() {
    return originalResponse;
  }

  public boolean isExpired() {
    return originalResponse.isExpired();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SsdpService that = (SsdpService) o;

    if (!serialNumber.equals(that.serialNumber)) return false;
    return serviceType.equals(that.serviceType);
  }

  @Override
  public int hashCode() {
    int result = serialNumber.hashCode();
    result = 31 * result + serviceType.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "SsdpService{" +
        "serialNumber='" + serialNumber + '\'' +
        ", serviceType='" + serviceType + '\'' +
        ", location='" + location + '\'' +
        ", remoteIp=" + remoteIp +
        '}';
  }

  // END GENERATED CODE
}
