package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

/**
 * PaymentResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-05-24T21:19:14.382432600+02:00[Europe/Paris]")
public class PaymentResponse {
    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;

    @JsonProperty("checkout_id")
    private UUID checkoutId;

    public PaymentResponse paymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    /**
     * Get paymentStatus
     *
     * @return paymentStatus
     */
    @ApiModelProperty(value = "")

    @Valid

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentResponse checkoutId(UUID checkoutId) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentResponse paymentResponse = (PaymentResponse) o;
        return Objects.equals(this.paymentStatus, paymentResponse.paymentStatus) &&
                Objects.equals(this.checkoutId, paymentResponse.checkoutId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentStatus, checkoutId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PaymentResponse {\n");

        sb.append("    paymentStatus: ").append(toIndentedString(paymentStatus)).append("\n");
        sb.append("    checkoutId: ").append(toIndentedString(checkoutId)).append("\n");
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

