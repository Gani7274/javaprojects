package com.example.recharge;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Recharge {

    private Long rechargeId;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "[0-9]{10}", message = "Mobile number must contain 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Operator is required")
    private String operator;

    @NotNull(message = "Amount is required")
    @Min(value = 10, message = "Minimum recharge amount is 10")
    private Double amount;

    @NotBlank(message = "Plan type is required")
    private String planType;

    // Default No-argument constructor
    public Recharge() {
    }

    // Constructor with fields
    public Recharge(Long rechargeId, String mobileNumber, String operator, Double amount, String planType) {
        this.rechargeId = rechargeId;
        this.mobileNumber = mobileNumber;
        this.operator = operator;
        this.amount = amount;
        this.planType = planType;
    }

    // Getters and Setters
    public Long getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(Long rechargeId) {
        this.rechargeId = rechargeId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }
}