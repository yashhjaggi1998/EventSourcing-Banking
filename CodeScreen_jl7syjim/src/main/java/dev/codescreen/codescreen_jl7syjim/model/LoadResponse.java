package dev.codescreen.codescreen_jl7syjim.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * The result of an load.
 */

@Schema(name = "LoadResponse", description = "The result of an load.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-29T18:15:12.258460-07:00[America/Los_Angeles]", comments = "Generator version: 7.5.0")
public class LoadResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private String userId;

  private String messageId;

  private Amount balance;

  private ResponseCode responseCode;

  public void setResponseCode(ResponseCode responseCode) {
    this.responseCode = responseCode;
  }

  public ResponseCode getResponseCode() {
    return responseCode;
  }

  public LoadResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public LoadResponse(String userId, String messageId, Amount balance, ResponseCode responseCode) {
    this.userId = userId;
    this.messageId = messageId;
    this.balance = balance;
    this.responseCode = responseCode;
  }

  public LoadResponse userId(String userId) {
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

  public LoadResponse messageId(String messageId) {
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

  public LoadResponse balance(Amount balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * @return balance
  */
  @NotNull @Valid 
  @Schema(name = "balance", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("balance")
  public Amount getBalance() {
    return balance;
  }

  public void setBalance(Amount balance) {
    this.balance = balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoadResponse loadResponse = (LoadResponse) o;
    return Objects.equals(this.userId, loadResponse.userId) &&
        Objects.equals(this.messageId, loadResponse.messageId) &&
        Objects.equals(this.balance, loadResponse.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, messageId, balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoadResponse {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    messageId: ").append(toIndentedString(messageId)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
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

