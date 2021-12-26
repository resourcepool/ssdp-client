package io.resourcepool.ssdp.model;

/**
 * This class represents the SsdpClient Options.
 * Defaults are shown in static final fields.
 */
public class SsdpClientOptions {

  private Boolean ignoreInterfaceDiscoveryErrors = false;
  private Boolean lookupAllIncomingAnnouncements = true;

  public Boolean getIgnoreInterfaceDiscoveryErrors() {
    return ignoreInterfaceDiscoveryErrors;
  }

  public Boolean getLookupAllIncomingAnnouncements() {
    return lookupAllIncomingAnnouncements;
  }

  // BEGIN GENERATED CODE

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private Boolean ignoreInterfaceErrors = false;
    private Boolean lookupAllIncomingAnnouncements = true;

    private Builder() {
    }

    /**
     * Ignore Interface Errors.
     * Specifically aimed at fails happening when joining group on all interfaces.
     * Default behaviour is to fail on first error.
     * Using this method will silently ignore those errors and proceed with other interfaces.
     * All reported errors can be retrieved by overriding the onFailedAndIgnored method in the DiscoveryListener.
     * @see io.resourcepool.ssdp.model.DiscoveryListener#onFailedAndIgnored(Exception)
     * @return the current builder
     */
    public Builder ignoreInterfaceDiscoveryErrors() {
      this.ignoreInterfaceErrors = true;
      return this;
    }

    /**
     * Disable Auto Lookup.
     * When receiving incoming announcements, the default behaviour is to send a discovery request to look it up.
     * This behaviour can be disabled with this method.
     * @return the current builder
     */
    public Builder disableAutoLookup() {
      this.lookupAllIncomingAnnouncements = false;
      return this;
    }

    public SsdpClientOptions build() {
      SsdpClientOptions discoveryOptions = new SsdpClientOptions();
      discoveryOptions.ignoreInterfaceDiscoveryErrors = this.ignoreInterfaceErrors;
      return discoveryOptions;
    }
  }

  // END GENERATED CODE
}
