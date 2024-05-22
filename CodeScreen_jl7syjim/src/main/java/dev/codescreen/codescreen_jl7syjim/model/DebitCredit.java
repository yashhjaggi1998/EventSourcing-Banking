package dev.codescreen.codescreen_jl7syjim.model;

import com.fasterxml.jackson.annotation.JsonValue;


import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Debit or Credit flag for the network transaction. A Debit deducts funds from a user. A credit adds funds to a user.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-29T18:15:12.258460-07:00[America/Los_Angeles]", comments = "Generator version: 7.5.0")
public enum DebitCredit {
  
  DEBIT("DEBIT"),
  
  CREDIT("CREDIT");

  private String value;

  DebitCredit(String value) {
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
  public static DebitCredit fromValue(String value) {
    for (DebitCredit b : DebitCredit.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

