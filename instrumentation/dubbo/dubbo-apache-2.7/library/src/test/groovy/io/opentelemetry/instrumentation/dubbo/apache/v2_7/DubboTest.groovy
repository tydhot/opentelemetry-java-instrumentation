/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.instrumentation.dubbo.apache.v2_7

import io.opentelemetry.instrumentation.dubbo.apache.v2_7.api.HelloService
import io.opentelemetry.instrumentation.dubbo.apache.v2_7.impl.HelloServiceImpl
import io.opentelemetry.instrumentation.test.InstrumentationTestTrait
import org.apache.dubbo.config.ReferenceConfig
import org.apache.dubbo.config.RegistryConfig
import org.apache.dubbo.config.ServiceConfig

class DubboTest extends AbstractDubboTest implements InstrumentationTestTrait {
  @Override
  ServiceConfig configureServer() {
    def registerConfig = new RegistryConfig()
    registerConfig.setAddress("N/A")
    ServiceConfig<HelloServiceImpl> service = new ServiceConfig<>()
    service.setInterface(HelloService.class)
    service.setRef(new HelloServiceImpl())
    service.setRegistry(registerConfig)
    service.setFilter("OtelServerFilter")
    return service
  }

  @Override
  ReferenceConfig configureClient(int port) {
    ReferenceConfig<HelloService> reference = new ReferenceConfig<>()
    reference.setInterface(HelloService.class)
    reference.setGeneric("true")
    reference.setUrl("dubbo://localhost:" + port)
    reference.setFilter("OtelClientFilter")
    return reference
  }
}