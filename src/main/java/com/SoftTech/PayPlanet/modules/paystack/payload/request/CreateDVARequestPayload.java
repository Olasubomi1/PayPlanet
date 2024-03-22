package com.SoftTech.PayPlanet.modules.paystack.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CreateDVARequestPayload {
    private int customerCode;
    private String preferredBank;
    private String firstName;
    private String lastName;
    private String phone;

    @SerializedName("callback_url")  // For Gson library
    @JsonProperty("callback_url")    // For springBoot.
    private String callbackUrl;
}
