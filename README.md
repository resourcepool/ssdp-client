# jarpic-client
A Simple Asynchronous SSDP/1.0 UPNP/1.1 Java Client using JDK APIs only.

This library works on Android as well.


[![Build Status](https://travis-ci.org/resourcepool/ssdp-client.svg?branch=master)](https://travis-ci.org/resourcepool/ssdp-client)

## Add it to your project
Maven:
```xml
<!-- https://mvnrepository.com/artifact/io.resourcepool/ssdp-client -->
<dependency>
    <groupId>io.resourcepool</groupId>
    <artifactId>ssdp-client</artifactId>
    <version>2.0.0</version>
</dependency>
```
Gradle:
```groovy
compile 'io.resourcepool:ssdp-client:2.0.0'
```

## Changelog

### 2.0.0
 * Put Client builder as static
 * Support Update announcement of SSDP
 * Refactored packages (get ready for Java 9 module one day)
### 1.2.0
 * Fixed NPE when no Serial Number
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

Discover specific SSDP service by serviceType:

```java
    SsdpClient client = SsdpClient.create();
    DiscoveryRequest networkStorageDevice = DiscoveryRequest.builder()
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

When you're done, don't forget to stop the discovery:
```java
ssdpClient.stopDiscovery();
```

## License
   Copyright 2017 Resourcepool

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
