package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PaymentOrder
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-05-24T21:19:14.382432600+02:00[Europe/Paris]")
public class PaymentOrder   {
  @JsonProperty("seller_account")
  private String sellerAccount;

  @JsonProperty("amount")
  private Double amount;

  @JsonProperty("currency")
  private String currency;

  @JsonProperty("payment_order_id")
  private UUID paymentOrderId;

  public PaymentOrder sellerAccount(String sellerAccount) {
    this.sellerAccount = sellerAccount;
    return this;
  }

  /**
   * Get sellerAccount
   * @return sellerAccount
  */
  @ApiModelProperty(example = "John Doe", value = "")


  public String getSellerAccount() {
    return sellerAccount;
  }

  public void setSellerAccount(String sellerAccount) {
    this.sellerAccount = sellerAccount;
  }

  public PaymentOrder amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  @ApiModelProperty(example = "10.5", value = "")


  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public PaymentOrder currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Currency of the amount respecting ISO 4217
   * @return currency
  */
  @ApiModelProperty(example = "EUR", value = "Currency of the amount respecting ISO 4217")

@Size(min=3,max=4) 
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public PaymentOrder paymentOrderId(UUID paymentOrderId) {
    this.paymentOrderId = paymentOrderId;
    return this;
  }

  /**
   * Get paymentOrderId
   * @return paymentOrderId
  */
  @ApiModelProperty(example = "f8a8c8e7-b8b6-4b8b-8b8b-8b8b8b8b8b8b", value = "")

  @Valid

  public UUID getPaymentOrderId() {
    return paymentOrderId;
  }

  public void setPaymentOrderId(UUID paymentOrderId) {
    this.paymentOrderId = paymentOrderId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentOrder paymentOrder = (PaymentOrder) o;
    return Objects.equals(this.sellerAccount, paymentOrder.sellerAccount) &&
        Objects.equals(this.amount, paymentOrder.amount) &&
        Objects.equals(this.currency, paymentOrder.currency) &&
        Objects.equals(this.paymentOrderId, paymentOrder.paymentOrderId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sellerAccount, amount, currency, paymentOrderId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentOrder {\n");
    
    sb.append("    sellerAccount: ").append(toIndentedString(sellerAccount)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    paymentOrderId: ").append(toIndentedString(paymentOrderId)).append("\n");
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

