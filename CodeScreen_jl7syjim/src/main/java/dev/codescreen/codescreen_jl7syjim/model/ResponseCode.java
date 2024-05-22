package dev.codescreen.codescreen_jl7syjim.model;

import com.fasterxml.jackson.annotation.JsonValue;


import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The response code sent back to the network for the merchant. Multiple declines reasons may exist but only one will be sent back to the network. Advice messages will include the response code that was sent on our behalf.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-29T18:15:12.258460-07:00[America/Los_Angeles]", comments = "Generator version: 7.5.0")
public enum ResponseCode {
  
  APPROVED("APPROVED"),
  
  DECLINED("DECLINED");

  private String value;

  ResponseCode(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ResponseCode fromValue(String value) {
    for (ResponseCode b : ResponseCode.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

