/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 */
package org.jclouds.ibmdev;

import java.net.URI;
import java.util.Date;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.jclouds.http.filters.BasicAuthentication;
import org.jclouds.ibmdev.domain.Address;
import org.jclouds.ibmdev.domain.Image;
import org.jclouds.ibmdev.domain.Instance;
import org.jclouds.ibmdev.domain.Key;
import org.jclouds.ibmdev.domain.Location;
import org.jclouds.ibmdev.domain.Volume;
import org.jclouds.ibmdev.functions.GetFirstInstanceInList;
import org.jclouds.ibmdev.functions.ParseAddressFromJson;
import org.jclouds.ibmdev.functions.ParseAddressesFromJson;
import org.jclouds.ibmdev.functions.ParseExpirationTimeFromJson;
import org.jclouds.ibmdev.functions.ParseImageFromJson;
import org.jclouds.ibmdev.functions.ParseImagesFromJson;
import org.jclouds.ibmdev.functions.ParseInstanceFromJson;
import org.jclouds.ibmdev.functions.ParseInstancesFromJson;
import org.jclouds.ibmdev.functions.ParseKeyFromJson;
import org.jclouds.ibmdev.functions.ParseKeysFromJson;
import org.jclouds.ibmdev.functions.ParseLongFromDate;
import org.jclouds.ibmdev.functions.ParseVolumeFromJson;
import org.jclouds.ibmdev.functions.ParseVolumesFromJson;
import org.jclouds.ibmdev.options.CreateInstanceOptions;
import org.jclouds.ibmdev.options.RestartInstanceOptions;
import org.jclouds.ibmdev.xml.LocationHandler;
import org.jclouds.ibmdev.xml.LocationsHandler;
import org.jclouds.rest.annotations.EndpointParam;
import org.jclouds.rest.annotations.ExceptionParser;
import org.jclouds.rest.annotations.FormParams;
import org.jclouds.rest.annotations.ParamParser;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.ResponseParser;
import org.jclouds.rest.annotations.SkipEncoding;
import org.jclouds.rest.annotations.XMLResponseParser;
import org.jclouds.rest.functions.ReturnNullOnNotFoundOr404;
import org.jclouds.rest.functions.ReturnVoidOnNotFoundOr404;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * Provides asynchronous access to IBMDeveloperCloud via their REST API.
 * <p/>
 * 
 * @see IBMDeveloperCloudClient
 * @see <a href="http://www-180.ibm.com/cloud/enterprise/beta/support" />
 * @author Adrian Cole
 */
@RequestFilters(BasicAuthentication.class)
@Consumes(MediaType.APPLICATION_JSON)
@SkipEncoding( { '{', '}' })
public interface IBMDeveloperCloudAsyncClient {
   public static final String VERSION = "20090403";

   /**
    * @see IBMDeveloperCloudClient#listImages()
    */
   @GET
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/images")
   @ResponseParser(ParseImagesFromJson.class)
   ListenableFuture<Set<? extends Image>> listImages();

   /**
    * @see IBMDeveloperCloudClient#getImage(long)
    */
   @GET
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/images/{imageId}")
   @ResponseParser(ParseImageFromJson.class)
   ListenableFuture<Image> getImage(@PathParam("imageId") String id);

   /**
    * @see IBMDeveloperCloudClient#deleteImage
    */
   @DELETE
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/images/{imageId}")
   @ExceptionParser(ReturnVoidOnNotFoundOr404.class)
   ListenableFuture<Void> deleteImage(@PathParam("imageId") String id);

   /**
    * @see IBMDeveloperCloudClient#setImageVisibility(long, Image.Visibility)
    */
   @PUT
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/images/{imageId}")
   @ResponseParser(ParseImageFromJson.class)
   ListenableFuture<Image> setImageVisibility(@PathParam("imageId") String id,
            @FormParam("visibility") Image.Visibility visibility);

   /**
    * @see IBMDeveloperCloudClient#listInstancesFromRequest(long)
    */
   @GET
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/requests/{requestId}")
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   @ResponseParser(ParseInstancesFromJson.class)
   ListenableFuture<Set<? extends Instance>> listInstancesFromRequest(
            @PathParam("requestId") String requestId);

   /**
    * @see IBMDeveloperCloudClient#listInstances()
    */
   @GET
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/instances")
   @ResponseParser(ParseInstancesFromJson.class)
   ListenableFuture<Set<? extends Instance>> listInstances();

   /**
    * @see IBMDeveloperCloudClient#getInstance(long)
    */
   @GET
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/instances/{instanceId}")
   @ResponseParser(ParseInstanceFromJson.class)
   ListenableFuture<Instance> getInstance(@PathParam("instanceId") String id);

   /**
    * 
    * @see IBMDeveloperCloudClient#extendReservationForInstance(long,Date)
    */
   @PUT
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/instances/{instanceId}")
   @ResponseParser(ParseExpirationTimeFromJson.class)
   ListenableFuture<Date> extendReservationForInstance(@PathParam("instanceId") String id,
            @FormParam("expirationTime") @ParamParser(ParseLongFromDate.class) Date expirationTime);

   /**
    * @see IBMDeveloperCloudClient#restartInstance
    */
   @PUT
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/instances/{instanceId}")
   @FormParams(keys = "state", values = "restart")
   ListenableFuture<Void> restartInstance(@PathParam("instanceId") String id,
            RestartInstanceOptions... options);

   /**
    * @see IBMDeveloperCloudClient#saveInstanceToImage
    */
   @PUT
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/instances/{instanceId}")
   @FormParams(keys = "state", values = "save")
   @ResponseParser(ParseImageFromJson.class)
   ListenableFuture<Image> saveInstanceToImage(@PathParam("instanceId") String id,
            @FormParam("name") String toImageName,
            @FormParam("description") String toImageDescription);

   /**
    * @see IBMDeveloperCloudClient#createInstanceInLocation
    */
   @POST
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/instances")
   @ResponseParser(GetFirstInstanceInList.class)
   ListenableFuture<Instance> createInstanceInLocation(@FormParam("location") String location,
            @FormParam("name") String name, @FormParam("imageID") String imageID,
            @FormParam("instanceType") String instanceType, CreateInstanceOptions... options);

   /**
    * @see IBMDeveloperCloudClient#deleteInstance
    */
   @DELETE
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/instances/{instanceId}")
   @ExceptionParser(ReturnVoidOnNotFoundOr404.class)
   ListenableFuture<Void> deleteInstance(@PathParam("instanceId") String id);

   /**
    * @see IBMDeveloperCloudClient#listKeys()
    */
   @GET
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/keys")
   @ResponseParser(ParseKeysFromJson.class)
   ListenableFuture<Set<? extends Key>> listKeys();

   /**
    * @see IBMDeveloperCloudClient#generateKeyPair(String)
    */
   @POST
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/keys")
   @ResponseParser(ParseKeyFromJson.class)
   ListenableFuture<Key> generateKeyPair(@FormParam("name") String name);

   /**
    * @see IBMDeveloperCloudClient#addPublicKey(String, String)
    */
   @POST
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/keys")
   ListenableFuture<Void> addPublicKey(@FormParam("name") String name,
            @FormParam("publicKey") String publicKey);

   /**
    * @see IBMDeveloperCloudClient#updatePublicKey(String, String)
    */
   @PUT
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/keys/{keyName}")
   ListenableFuture<Void> updatePublicKey(@PathParam("keyName") String name,
            @FormParam("publicKey") String publicKey);

   /**
    * @see IBMDeveloperCloudClient#setDefaultStatusOfKey(String, boolean)
    */
   @PUT
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/keys/{keyName}")
   ListenableFuture<Void> setDefaultStatusOfKey(@PathParam("keyName") String name,
            @FormParam("default") boolean isDefault);

   /**
    * @see IBMDeveloperCloudClient#getKey(String)
    */
   @GET
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/keys/{keyName}")
   @ResponseParser(ParseKeyFromJson.class)
   ListenableFuture<Key> getKey(@PathParam("keyName") String name);

   /**
    * @see IBMDeveloperCloudClient#deleteKey
    */
   @DELETE
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/keys/{keyName}")
   @ExceptionParser(ReturnVoidOnNotFoundOr404.class)
   ListenableFuture<Void> deleteKey(@PathParam("keyName") String name);

   /**
    * @see IBMDeveloperCloudClient#listVolumes()
    */
   @GET
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/storage")
   @ResponseParser(ParseVolumesFromJson.class)
   ListenableFuture<Set<? extends Volume>> listVolumes();

   /**
    * @see IBMDeveloperCloudClient#createVolumeInLocation
    */
   @POST
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/storage")
   @ResponseParser(ParseVolumeFromJson.class)
   ListenableFuture<Volume> createVolumeInLocation(@FormParam("location") String location,
            @FormParam("name") String name, @FormParam("format") String format,
            @FormParam("size") String size);

   /**
    * @see IBMDeveloperCloudClient#getVolume(long)
    */
   @GET
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/storage/{volumeId}")
   @ResponseParser(ParseVolumeFromJson.class)
   ListenableFuture<Volume> getVolume(@PathParam("volumeId") String id);

   /**
    * @see IBMDeveloperCloudClient#deleteVolume
    */
   @DELETE
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/storage/{volumeId}")
   @ExceptionParser(ReturnVoidOnNotFoundOr404.class)
   ListenableFuture<Void> deleteVolume(@PathParam("volumeId") String id);

   /**
    * @see IBMDeveloperCloudClient#listLocations()
    */
   @GET
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/locations")
   @Consumes(MediaType.TEXT_XML)
   @XMLResponseParser(LocationsHandler.class)
   ListenableFuture<Set<? extends Location>> listLocations();

   /**
    * @see IBMDeveloperCloudClient#getLocation
    */
   @GET
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/locations/{locationId}")
   @Consumes(MediaType.TEXT_XML)
   @XMLResponseParser(LocationHandler.class)
   ListenableFuture<Location> getLocation(@PathParam("locationId") String id);

   /**
    * @see IBMDeveloperCloudClient#listAddresses()
    */
   @GET
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/addresses")
   @ResponseParser(ParseAddressesFromJson.class)
   ListenableFuture<Set<? extends Address>> listAddresses();

   /**
    * @see IBMDeveloperCloudClient#allocateAddressInLocation
    */
   @POST
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/addresses")
   @ResponseParser(ParseAddressFromJson.class)
   ListenableFuture<Address> allocateAddressInLocation(@FormParam("location") String locationId);

   /**
    * @see IBMDeveloperCloudClient#releaseAddress
    */
   @DELETE
   @Path(IBMDeveloperCloudAsyncClient.VERSION + "/addresses/{addressId}")
   @ExceptionParser(ReturnVoidOnNotFoundOr404.class)
   ListenableFuture<Void> releaseAddress(@PathParam("addressId") String id);

   /**
    * @see IBMDeveloperCloudClient#getManifest
    */
   @GET
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   @Consumes(MediaType.TEXT_XML)
   ListenableFuture<String> getManifest(@EndpointParam URI manifest);
}