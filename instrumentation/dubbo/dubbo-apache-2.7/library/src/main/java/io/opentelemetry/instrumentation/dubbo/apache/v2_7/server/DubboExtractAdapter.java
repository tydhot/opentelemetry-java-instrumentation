/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.instrumentation.dubbo.apache.v2_7.server;

import io.opentelemetry.context.propagation.TextMapPropagator;
import org.apache.dubbo.rpc.RpcInvocation;

class DubboExtractAdapter implements TextMapPropagator.Getter<RpcInvocation> {

  static final DubboExtractAdapter GETTER = new DubboExtractAdapter();

  @Override
  public Iterable<String> keys(RpcInvocation invocation) {
    return invocation.getAttachments().keySet();
  }

  @Override
  public String get(RpcInvocation carrier, String key) {
    return carrier.getAttachment(key);
  }
}
