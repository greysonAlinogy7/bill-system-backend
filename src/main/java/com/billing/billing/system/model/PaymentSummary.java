package com.billing.billing.system.model;

import com.billing.billing.system.domain.PaymentType;
import lombok.Data;

@Data
public class PaymentSummary {
    private PaymentType paymentType;
    private Double totalAmount;
    private int transactionCount;
    private double percentage;
}
