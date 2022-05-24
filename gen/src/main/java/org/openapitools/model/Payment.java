package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Payment
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-05-24T21:19:14.382432600+02:00[Europe/Paris]")
public class Payment {
    @JsonProperty("buyer_info")
    private BuyerInfo buyerInfo;

    @JsonProperty("checkout_id")
    private UUID checkoutId;

    @JsonProperty("credit_card_info")
    private String creditCardInfo;

    @JsonProperty("payment_orders")
    @Valid
    private List<PaymentOrder> paymentOrders = null;

    public Payment buyerInfo(BuyerInfo buyerInfo) {
        this.buyerInfo = buyerInfo;
        return this;
    }

    /**
     * Get buyerInfo
     *
     * @return buyerInfo
     */
    @ApiModelProperty(value = "")

    @Valid

    public BuyerInfo getBuyerInfo() {
        return buyerInfo;
    }

    public void setBuyerInfo(BuyerInfo buyerInfo) {
        this.buyerInfo = buyerInfo;
    }

    public Payment checkoutId(UUID checkoutId) {
        this.checkoutId = checkoutId;
        return this;
    }

    /**
     * Get checkoutId
     *
     * @return checkoutId
     */
    @ApiModelProperty(example = "f8a8c8e7-b8b6-4b8b-8b8b-8b8b8b8b8b8b", value = "")

    @Valid

    public UUID getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(UUID checkoutId) {
        this.checkoutId = checkoutId;
    }

    public Payment creditCardInfo(String creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
        return this;
    }

    /**
     * Encrypted credit card information.
     *
     * @return creditCardInfo
     */
    @ApiModelProperty(example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c", value = "Encrypted credit card information.")


    public String getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(String creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }

    public Payment paymentOrders(List<PaymentOrder> paymentOrders) {
        this.paymentOrders = paymentOrders;
        return this;
    }

    public Payment addPaymentOrdersItem(PaymentOrder paymentOrdersItem) {
        if (this.paymentOrders == null) {
            this.paymentOrders = new ArrayList<>();
        }
        this.paymentOrders.add(paymentOrdersItem);
        return this;
    }

    /**
     * Get paymentOrders
     *
     * @return paymentOrders
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<PaymentOrder> getPaymentOrders() {
        return paymentOrders;
    }

    public void setPaymentOrders(List<PaymentOrder> paymentOrders) {
        this.paymentOrders = paymentOrders;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Payment payment = (Payment) o;
        return Objects.equals(this.buyerInfo, payment.buyerInfo) &&
                Objects.equals(this.checkoutId, payment.checkoutId) &&
                Objects.equals(this.creditCardInfo, payment.creditCardInfo) &&
                Objects.equals(this.paymentOrders, payment.paymentOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerInfo, checkoutId, creditCardInfo, paymentOrders);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Payment {\n");

        sb.append("    buyerInfo: ").append(toIndentedString(buyerInfo)).append("\n");
        sb.append("    checkoutId: ").append(toIndentedString(checkoutId)).append("\n");
        sb.append("    creditCardInfo: ").append(toIndentedString(creditCardInfo)).append("\n");
        sb.append("    paymentOrders: ").append(toIndentedString(paymentOrders)).append("\n");
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

