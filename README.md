Weather App
=====

This repo contains the code for Group 10's Hi-Fi prototype for the University of Cambridge Computer Science IA Interaction Design course 2018-2019. 

Setup
---
Copy `src/main/resources/secrets-template.properties` to `src/main/resources/secrets.properties` and insert API Keys.

Building / Running
---
After setting up the API Keys, a jar can easily be built using `gradlew jar` or `./gradlew jar` (depending on your platform).
The resulting jar includes all the needed graphics and libraries to run independently and  will be located at `build/libs/group10.jar`.
It can be run using `java -jar build/libs/group10.jar`

Alternatively opening this repo with an IDE which supports Gradle (e.g. IntelliJ) which should automatically setup the project.
The entry point to run is `uk.ac.cam.cl.interactiondesign.group10.Main`

The project requires Java 8 has been tested on Windows 10 and Ubuntu 19.04

Libraries
---

Direct dependencies (the libraries themselves have dependencies on other libraries):

 - Java Client For Google Maps Services (`com.google.maps:google-maps-services:0.9.3`).
   Licensed under the Apache 2.0 License, used for easy integration with Google's Geocoding API
 
 - Unirest Java - Simplified, lightweight HTTP client library (`com.mashape.unirest:unirest-java:1.4.9`).
   Licensed under the MIT License, used to simplify making HTTP requests to Dark Sky's API. 
