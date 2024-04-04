package com.SoftTech.PayPlanet.modules.paystack.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateDVARequestPayload {
    private String customerCode;
    private String preferredBank;
    private String firstName;
    private String lastName;
    private String phone;

    @SerializedName("callback_url")  // For Gson library
    @JsonProperty("callback_url")    // For springBoot.
    private String callbackUrl;
}
