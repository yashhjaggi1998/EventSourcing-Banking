package dev.codescreen.codescreen_jl7syjim.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Authorization request that needs to be processed.
 */

@Schema(name = "AuthorizationRequest", description = "Authorization request that needs to be processed.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-29T18:15:12.258460-07:00[America/Los_Angeles]", comments = "Generator version: 7.5.0")
public class AuthorizationRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String userId;

  private String messageId;

  private Amount transactionAmount;

  public AuthorizationRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AuthorizationRequest(String userId, String messageId, Amount transactionAmount) {
    this.userId = userId;
    this.messageId = messageId;
    this.transactionAmount = transactionAmount;
  }

  public AuthorizationRequest userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  @NotNull @Size(min = 1) 
  @Schema(name = "userId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userId")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public AuthorizationRequest messageId(String messageId) {
    this.messageId = messageId;
    return this;
  }

  /**
   * Get messageId
   * @return messageId
  */
  @NotNull @Size(min = 1) 
  @Schema(name = "messageId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("messageId")
  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public AuthorizationRequest transactionAmount(Amount transactionAmount) {
    this.transactionAmount = transactionAmount;
    return this;
  }

  /**
   * Get transactionAmount
   * @return transactionAmount
  */
  @NotNull @Valid 
  @Schema(name = "transactionAmount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("transactionAmount")
  public Amount getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(Amount transactionAmount) {
    this.transactionAmount = transactionAmount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorizationRequest authorizationRequest = (AuthorizationRequest) o;
    return Objects.equals(this.userId, authorizationRequest.userId) &&
        Objects.equals(this.messageId, authorizationRequest.messageId) &&
        Objects.equals(this.transactionAmount, authorizationRequest.transactionAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, messageId, transactionAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthorizationRequest {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    messageId: ").append(toIndentedString(messageId)).append("\n");
    sb.append("    transactionAmount: ").append(toIndentedString(transactionAmount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

