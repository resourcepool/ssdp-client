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
        try{
            return "NoSerialNumberException{" +
                    "response=" + response +
                    '}';
        } catch (NullPointerException e) {
            System.err.println("NullPointerException occurred: " + e.getMessage());
            return "NoSerialNumberException{response=null}";
        } catch (Exception e) {
            System.err.println("An unexpected exception occurred: " + e.getMessage());
            return "NoSerialNumberException{unexpected error}";
        }
    }

}
