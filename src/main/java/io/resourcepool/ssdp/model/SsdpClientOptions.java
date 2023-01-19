package io.resourcepool.ssdp.model;

import io.resourcepool.ssdp.client.SsdpParams;

/**
 * This class represents the SsdpClient Options.
 * Defaults are shown in static final fields.
 */
public class SsdpClientOptions {

    private Boolean ignoreInterfaceDiscoveryErrors = false;
    private Boolean lookupAllIncomingAnnouncements = true;

    private int bindingPort = SsdpParams.getSsdpMulticastDefaultPort();

    /**
     * Allow the program to process the discovery response.
     */
    private Boolean useCache = true;


    public Boolean getIgnoreInterfaceDiscoveryErrors() {
        return ignoreInterfaceDiscoveryErrors;
    }

    public Boolean getLookupAllIncomingAnnouncements() {
        return lookupAllIncomingAnnouncements;
    }

    public Boolean getUseCache() {
        return useCache;
    }

    public int getBindingPort() {
        return bindingPort;
    }

    // BEGIN GENERATED CODE

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private Boolean ignoreInterfaceErrors = false;
        private Boolean lookupAllIncomingAnnouncements = true;
        private Boolean useCache = true;

        private int bindingPort = SsdpParams.getSsdpMulticastDefaultPort();

        private Builder() {
        }

        /**
         * Ignore Interface Errors.
         * Specifically aimed at fails happening when joining group on all interfaces.
         * Default behaviour is to fail on first error.
         * Using this method will silently ignore those errors and proceed with other interfaces.
         * All reported errors can be retrieved by overriding the onFailedAndIgnored method in the DiscoveryListener.
         *
         * @return the current builder
         * @see io.resourcepool.ssdp.model.DiscoveryListener#onFailedAndIgnored(Exception)
         */
        public Builder ignoreInterfaceDiscoveryErrors() {
            this.ignoreInterfaceErrors = true;
            return this;
        }

        /**
         * Disable Auto Lookup.
         * When receiving incoming announcements, the default behaviour is to send a discovery request to look it up.
         * This behaviour can be disabled with this method.
         *
         * @return the current builder
         */
        public Builder disableAutoLookup() {
            this.lookupAllIncomingAnnouncements = false;
            return this;
        }

        /**
         * Overrides Binding Port.
         * Sometimes, one may need to use another source binding port other than 1900.
         * Note that the SSDP multicast default port will not be altered by this. Only affects binding port.
         * @return the current builder
         */
        public Builder overrideBindingPort(int port) {
            this.bindingPort = port;
            return this;
        }

        /**
         * Disables caching SSDP entries
         * This behaviour disables caching for SSDP entries.
         * @return the current builder
         */
        public Builder disableCache() {
            this.useCache = false;
            return this;
        }

        public SsdpClientOptions build() {
            SsdpClientOptions discoveryOptions = new SsdpClientOptions();
            discoveryOptions.ignoreInterfaceDiscoveryErrors = this.ignoreInterfaceErrors;
            discoveryOptions.useCache = this.useCache;
            discoveryOptions.lookupAllIncomingAnnouncements = this.lookupAllIncomingAnnouncements;
            discoveryOptions.bindingPort = this.bindingPort;
            return discoveryOptions;
        }
    }

    // END GENERATED CODE
}
