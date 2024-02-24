package com.SoftTech.PayPlanet.modules.user.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "planet_user")
@Data
@Getter
@Setter
public class PlanetUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "emailVerified")
    private LocalDateTime emailVerifiedAt;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "auth_token")
    private String authToken;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "user_role_id")
    private Long userRoleId;

    @Column(name = "photo_link")
    private String photoLink;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "login_attempt")
    private int loginAttempt;

    @Column(name = "auth_token_creation_date")
    private LocalDateTime authTokenCreationDate;

    @Column(name = "auth_token_expiration_date")
    private LocalDateTime authTokenExpirationDate;

    @Column(name = "otp")
    private String otp;

    @Column(name = "otp_created_date")
    private LocalDateTime otpCreatedDate;

    @Column(name = "otp_exp_date")
    private LocalDateTime otpExpDate;

    @Column(name = "is_otp_verified")
    private boolean isOtpVerified;

    @Column(name = "user_status")
    private String userStatus;
}

