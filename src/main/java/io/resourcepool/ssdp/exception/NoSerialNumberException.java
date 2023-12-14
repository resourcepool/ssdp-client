package io.resourcepool.ssdp.exception;

import io.resourcepool.ssdp.client.response.SsdpResponse;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 11/11/2017
 */
public class NoSerialNumberException extends RuntimeException {

    private SsdpResponse response;
    public NoSerialNumberException() {

    }
    public NoSerialNumberException(SsdpResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "NoSerialNumberException{" +
                "response=" + response +
                '}';
    }

}
