/**
 *
 * Copyright (C) 2009 Cloud Conscious, LLC. <info@cloudconscious.com>
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
package org.jclouds.atmosonline.saas.reference;

/**
 * Configuration properties and constants used in AtmosStorage connections.
 * 
 * @author Adrian Cole
 */
public interface AtmosStorageConstants {
   public static final String PROPERTY_EMCSAAS_ENDPOINT = "jclouds.emcsaas.endpoint";
   public static final String PROPERTY_EMCSAAS_UID = "jclouds.emcsaas.uid";
   public static final String PROPERTY_EMCSAAS_KEY = "jclouds.emcsaas.key";
   /**
    * how long do we wait before obtaining a new timestamp for requests.  Clocks must be within 5m of Atmos.
    */
   public static final String PROPERTY_EMCSAAS_SESSIONINTERVAL = "jclouds.emcsaas.sessioninterval";
   
   /**
    * longest time a single synchronous operation can take before throwing an exception.
    */
   public static final String PROPERTY_EMCSAAS_TIMEOUT = "jclouds.emcsaas.timeout";
   /**
    * time to pause before retrying a transient failure
    */
   public static final String PROPERTY_EMCSAAS_RETRY = "jclouds.emcsaas.retry";
}