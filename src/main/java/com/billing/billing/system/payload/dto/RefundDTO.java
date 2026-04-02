package com.billing.billing.system.payload.dto;


import com.billing.billing.system.domain.PaymentType;
import com.billing.billing.system.model.Branch;
import com.billing.billing.system.model.Order;
import com.billing.billing.system.model.ShiftReport;
import com.billing.billing.system.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class RefundDTO {
    private Long id;


    private OrderDTO order;
    private Long orderId;

    private String reason;
    private Double amount;


//    private ShiftReport shiftReport;
    private Long shiftReportId;


    private UserDTO cashier;
    private  String cashierName;


    private BranchDTO branch;
    private Long branchId;

    private PaymentType paymentType;


    private LocalDateTime createdAt;

}
