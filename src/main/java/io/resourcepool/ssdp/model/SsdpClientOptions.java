package io.resourcepool.ssdp.model;

/**
 * This class represents the SsdpClient Options.
 * Defaults are shown in static final fields.
 */
public class SsdpClientOptions {

  private Boolean ignoreInterfaceDiscoveryErrors = false;

  public Boolean getIgnoreInterfaceDiscoveryErrors() {
    return ignoreInterfaceDiscoveryErrors;
  }

  // BEGIN GENERATED CODE

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private Boolean ignoreInterfaceErrors = false;

    private Builder() {
    }

    /**
     * Ignore Interface Errors.
     * Specifically aimed at fails happening when joining group on all interfaces.
     * Default behaviour is to fail on first error.
     * Using this method will silently ignore those errors and proceed with other interfaces
     * @return the current builder
     */
    public Builder ignoreInterfaceDiscoveryErrors() {
      this.ignoreInterfaceErrors = true;
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
