package io.resourcepool.jarpic.model;

import io.resourcepool.jarpic.client.response.SsdpResponse;

import java.net.InetAddress;
import java.util.Map;

/**
 * This represents a SSDP Service.
 *
 * @author Loïc Ortola on 05/08/2017
 */
public class SsdpServiceAnnouncement {
  public enum Status {
    ALIVE, BYEBYE, UPDATE;

    /**
     * Parse NTS or ST header into a Status.
     *
     * @param nts the NTS or ST Header
     * @return the status or null if none match.
     */
    public static Status parse(String nts) {
      if ("ssdp:alive".equals(nts)) {
        return ALIVE;
      }
      if ("ssdp:byebye".equals(nts)) {
        return BYEBYE;
      }
      if ("ssdp:update".equals(nts)) {
        return UPDATE;
      }
      return null;
    }
  }

  private String serialNumber;
  private String serviceType;
  private String location;
  private Status status;
  private final InetAddress remoteIp;
  private final SsdpResponse originalResponse;


  /**
   * @param response the raw Ssdp response
   */
  public SsdpServiceAnnouncement(SsdpResponse response) {
    Map<String, String> headers = response.getHeaders();
    this.serialNumber = headers.get("USN");
    this.serviceType = headers.get("NT");
    this.status = Status.parse(headers.get("NTS"));
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

  public long getExpiry() {
    return originalResponse.getExpiry();
  }

  public Status getStatus() {
    return status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SsdpServiceAnnouncement that = (SsdpServiceAnnouncement) o;

    if (serialNumber != null ? !serialNumber.equals(that.serialNumber) : that.serialNumber != null) return false;
    if (serviceType != null ? !serviceType.equals(that.serviceType) : that.serviceType != null) return false;
    return status == that.status;
  }

  @Override
  public int hashCode() {
    int result = serialNumber != null ? serialNumber.hashCode() : 0;
    result = 31 * result + (serviceType != null ? serviceType.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "SsdpServiceAnnouncement{" +
        "serialNumber='" + serialNumber + '\'' +
        ", serviceType='" + serviceType + '\'' +
        ", location='" + location + '\'' +
        ", status=" + status +
        ", remoteIp=" + remoteIp +
        '}';
  }

  // END GENERATED CODE
}
