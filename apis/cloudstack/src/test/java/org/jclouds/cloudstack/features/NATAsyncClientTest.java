/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
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
 */
package org.jclouds.cloudstack.features;

import java.io.IOException;
import java.lang.reflect.Method;

import org.jclouds.cloudstack.options.CreateIPForwardingRuleOptions;
import org.jclouds.cloudstack.options.ListIPForwardingRulesOptions;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.functions.ParseFirstJsonValueNamed;
import org.jclouds.http.functions.UnwrapOnlyJsonValue;
import org.jclouds.http.functions.UnwrapOnlyNestedJsonValue;
import org.jclouds.rest.functions.MapHttp4xxCodesToExceptions;
import org.jclouds.rest.functions.ReturnEmptySetOnNotFoundOr404;
import org.jclouds.rest.functions.ReturnNullOnNotFoundOr404;
import org.jclouds.rest.internal.RestAnnotationProcessor;
import org.testng.annotations.Test;

import com.google.inject.TypeLiteral;

/**
 * Tests behavior of {@code NATAsyncClient}
 * 
 * @author Adrian Cole
 */
// NOTE:without testName, this will not call @Before* and fail w/NPE during
// surefire
@Test(groups = "unit", testName = "NATAsyncClientTest")
public class NATAsyncClientTest extends BaseCloudStackAsyncClientTest<NATAsyncClient> {
   public void testListIPForwardingRules() throws SecurityException, NoSuchMethodException, IOException {
      Method method = NATAsyncClient.class.getMethod("listIPForwardingRules", ListIPForwardingRulesOptions[].class);
      HttpRequest httpRequest = processor.createRequest(method);

      assertRequestLineEquals(httpRequest,
            "GET http://localhost:8080/client/api?response=json&command=listIpForwardingRules HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ParseFirstJsonValueNamed.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, ReturnEmptySetOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   public void testListIPForwardingRulesOptions() throws SecurityException, NoSuchMethodException, IOException {
      Method method = NATAsyncClient.class.getMethod("listIPForwardingRules", ListIPForwardingRulesOptions[].class);
      HttpRequest httpRequest = processor.createRequest(method,
            ListIPForwardingRulesOptions.Builder.virtualMachineId(3));

      assertRequestLineEquals(httpRequest,
            "GET http://localhost:8080/client/api?response=json&command=listIpForwardingRules&virtualmachineid=3 HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ParseFirstJsonValueNamed.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, ReturnEmptySetOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   public void testGetIPForwardingRule() throws SecurityException, NoSuchMethodException, IOException {
      Method method = NATAsyncClient.class.getMethod("getIPForwardingRule", long.class);
      HttpRequest httpRequest = processor.createRequest(method, 5);

      assertRequestLineEquals(httpRequest,
            "GET http://localhost:8080/client/api?response=json&command=listIpForwardingRules&id=5 HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, ReturnNullOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   public void testCreateIPForwardingRuleForVirtualMachine() throws SecurityException, NoSuchMethodException,
         IOException {
      Method method = NATAsyncClient.class.getMethod("createIPForwardingRule", long.class, String.class, int.class,
            CreateIPForwardingRuleOptions[].class);
      HttpRequest httpRequest = processor.createRequest(method, 7, "tcp", 22);

      assertRequestLineEquals(
            httpRequest,
            "GET http://localhost:8080/client/api?response=json&command=createIpForwardingRule&ipaddressid=7&startport=22&protocol=tcp HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, UnwrapOnlyJsonValue.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, MapHttp4xxCodesToExceptions.class);

      checkFilters(httpRequest);

   }

   public void testCreateIPForwardingRuleForVirtualMachineOptions() throws SecurityException, NoSuchMethodException,
         IOException {
      Method method = NATAsyncClient.class.getMethod("createIPForwardingRule", long.class, String.class, int.class,
            CreateIPForwardingRuleOptions[].class);
      HttpRequest httpRequest = processor.createRequest(method, 7, "tcp", 22,
            CreateIPForwardingRuleOptions.Builder.endPort(22));

      assertRequestLineEquals(
            httpRequest,
            "GET http://localhost:8080/client/api?response=json&command=createIpForwardingRule&ipaddressid=7&startport=22&protocol=tcp&endport=22 HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, UnwrapOnlyJsonValue.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, MapHttp4xxCodesToExceptions.class);

      checkFilters(httpRequest);

   }

   public void testEnableStaticNATForVirtualMachine() throws SecurityException, NoSuchMethodException, IOException {
      Method method = NATAsyncClient.class.getMethod("enableStaticNATForVirtualMachine", long.class, long.class);
      HttpRequest httpRequest = processor.createRequest(method, 5, 6);

      assertRequestLineEquals(httpRequest,
            "GET http://localhost:8080/client/api?response=json&command=enableStaticNat&virtualmachineid=5&ipaddressid=6 HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, UnwrapOnlyJsonValue.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, MapHttp4xxCodesToExceptions.class);

      checkFilters(httpRequest);

   }

   public void testDeleteIPForwardingRule() throws SecurityException, NoSuchMethodException, IOException {
      Method method = NATAsyncClient.class.getMethod("deleteIPForwardingRule", long.class);
      HttpRequest httpRequest = processor.createRequest(method, 5);

      assertRequestLineEquals(httpRequest,
            "GET http://localhost:8080/client/api?response=json&command=deleteIpForwardingRule&id=5 HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, UnwrapOnlyNestedJsonValue.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, ReturnNullOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   @Override
   protected TypeLiteral<RestAnnotationProcessor<NATAsyncClient>> createTypeLiteral() {
      return new TypeLiteral<RestAnnotationProcessor<NATAsyncClient>>() {
      };
   }
}
