# ssdp-client
A Simple Asynchronous SSDP/1.0 UPNP/1.1 Java Client using JDK APIs only.

This library works on Android as well.


[![Build Status](https://travis-ci.org/resourcepool/ssdp-client.svg?branch=master)](https://travis-ci.org/resourcepool/ssdp-client)

## Add it to your project

Jar Download:  
[https://mvnrepository.com/artifact/io.resourcepool/ssdp-client](https://mvnrepository.com/artifact/io.resourcepool/ssdp-client)


Maven:
```xml
<!-- https://mvnrepository.com/artifact/io.resourcepool/ssdp-client -->
<dependency>
    <groupId>io.resourcepool</groupId>
    <artifactId>ssdp-client</artifactId>
    <version>2.4.4</version>
</dependency>
```
Gradle:
```groovy
compile 'io.resourcepool:ssdp-client:2.4.4'
```

## Changelog

### 2.4.4
* #27 Fixed Android emulator issue

### 2.4.3
 * #22 Fixed multicast addressed datagrams
 * #23 Bumb versions

### 2.4.2
 * #21 Fixed notify message regex

### 2.4.1
 * #19 Fixed non-static builder method

### 2.4.0
 * #17 Fixed default options and newline bug
 * #18 Allow users to send packets without user-agent header
 * Version bumps

### 2.3.0
 * #9 Solved race condition on null callback
 * #13 Added custom interval between requests
 * #14 Added DiscoveryOptions and custom USER-AGENT and MX headers

### 2.2.0
 * #5 Solved discovery request cleaned after first call
 * #7 Solved regex parsing issue on headers with multiple spaced semicolumns

### 2.1.0
 * #4 Solved dynamic port on client socket binding

### 2.0.0
 * #2 Put Client builder as static
 * Support Update announcement of SSDP
 * Refactored packages (get ready for Java 9 module one day)
### 1.2.0
 * #1 Fixed NPE when no Serial Number
### 1.1.0
 * Resolved issue when closing socket
 * Updated docs
 
## Usage

Discover all SSDP services:

```java
    SsdpClient client = SsdpClient.create();
    DiscoveryRequest all = SsdpRequest.discoverAll();
    client.discoverServices(all, new DiscoveryListener() {
      @Override
      public void onServiceDiscovered(SsdpService service) {
        System.out.println("Found service: " + service);
      }

      @Override
      public void onServiceAnnouncement(SsdpServiceAnnouncement announcement) {
        System.out.println("Service announced something: " + announcement);
      }
    });
```

Discover specific SSDP service by serviceType (simple version):

```java
    SsdpClient client = SsdpClient.create();
    
    DiscoveryRequest networkStorageDevice = SsdpRequest.builder()
    .serviceType("urn:schemas-upnp-org:device:networkstoragedevice:1")
    .build();
    client.discoverServices(networkStorageDevice, new DiscoveryListener() {
      @Override
      public void onServiceDiscovered(SsdpService service) {
        System.out.println("Found service: " + service);
      }

      @Override
      public void onServiceAnnouncement(SsdpServiceAnnouncement announcement) {
        System.out.println("Service announced something: " + announcement);
      }
    });
```

Discover specific SSDP service by serviceType (with custom options):

```java
    SsdpClient client = SsdpClient.create();
    DiscoveryOptions options = DiscoveryOptions.builder()
    .intervalBetweenRequests(10000L) // optional interval between requests, defaults to 10 000 milliseconds
    .maxWaitTimeSeconds(3) // optional max wait time between req and response, defaults to 3 seconds
    .userAgent("Resourcepool SSDP Client") // optional custom user-agent, defaults to "Resourcepool SSDP Client"
    .build();

    DiscoveryRequest networkStorageDevice = SsdpRequest.builder()
    .serviceType("urn:schemas-upnp-org:device:networkstoragedevice:1")
    .discoveryOptions(options) // optional as well. 
    .build();
    client.discoverServices(networkStorageDevice, new DiscoveryListener() {
      @Override
      public void onServiceDiscovered(SsdpService service) {
        System.out.println("Found service: " + service);
      }

      @Override
      public void onServiceAnnouncement(SsdpServiceAnnouncement announcement) {
        System.out.println("Service announced something: " + announcement);
      }
    });
```


Discover specific SSDP service by serviceType (with custom options):

```java
    SsdpClient client = SsdpClient.create();
    DiscoveryOptions options = DiscoveryOptions.builder()
    .intervalBetweenRequests(10000L) // optional interval between requests, defaults to 10 000 milliseconds
    .maxWaitTimeSeconds(3) // optional max wait time between req and response, defaults to 3 seconds
    .userAgent("Resourcepool SSDP Client") // optional custom user-agent, defaults to "Resourcepool SSDP Client"
    .build();

    DiscoveryRequest networkStorageDevice = SsdpRequest.builder()
    .serviceType("urn:schemas-upnp-org:device:networkstoragedevice:1")
    .discoveryOptions(options) // optional as well. 
    .build();
    client.discoverServices(networkStorageDevice, new DiscoveryListener() {
      @Override
      public void onServiceDiscovered(SsdpService service) {
        System.out.println("Found service: " + service);
      }

      @Override
      public void onServiceAnnouncement(SsdpServiceAnnouncement announcement) {
        System.out.println("Service announced something: " + announcement);
      }
    });
```

Discovery is not a mandatory activity. You might just want to listen to announcements:
```java
    SsdpClient client = SsdpClient.create();
    client.discoverServices(null, new DiscoveryListener() {
      @Override
      public void onServiceDiscovered(SsdpService service) {
        System.out.println("Found service: " + service);
      }

      @Override
      public void onServiceAnnouncement(SsdpServiceAnnouncement announcement) {
        System.out.println("Service announced something: " + announcement);
      }
    });
```


Ignoring unavailable Multicast Interface errors 

```java
    SsdpClient client = SsdpClient.create();
    DiscoveryRequest all = SsdpRequest.discoverAll();
    client.discoverServices(all, SsdpClientOptions.builder().ignoreInterfaceDiscoveryErrors().build(), new DiscoveryListener() {
      @Override
      public void onServiceDiscovered(SsdpService service) {
        System.out.println("Found service: " + service);
      }

      @Override
      public void onServiceAnnouncement(SsdpServiceAnnouncement announcement) {
        System.out.println("Service announced something: " + announcement);
      }
    });
```


When you're done, don't forget to stop the discovery:
```java
ssdpClient.stopDiscovery();
```

## License
   Copyright 2016-2022 Resourcepool

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
