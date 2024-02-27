package com.SoftTech.PayPlanet.modules.paystack.orm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
public class CreateCustomerData {
    @SerializedName("id")
    @JsonProperty("id")
    private int id;

    @SerializedName("email")
    @JsonProperty("email")
    private String email;

    @SerializedName("first_name")
    @JsonProperty("first_name")
    private String firstName;

    @SerializedName("last_name")
    @JsonProperty("last_name")
    private String lastName;

    @SerializedName("integration")
    @JsonProperty("integration")
    private int integration;

    @SerializedName("domain")
    @JsonProperty("domain")
    private String domain;

    @SerializedName("customer_code")
    @JsonProperty("customer_dode")
    private String customerCode;

    @SerializedName("identified")
    @JsonProperty("identified")
    private boolean identified;

    private String createdAt;

    private String updatedAt;
}
