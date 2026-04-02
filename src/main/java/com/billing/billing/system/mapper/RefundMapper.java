package com.billing.billing.system.mapper;

import com.billing.billing.system.model.Refund;
import com.billing.billing.system.payload.dto.RefundDTO;

public class RefundMapper {
    public static RefundDTO toDTO(Refund refund){
        return RefundDTO.builder()
                .id(refund.getId())
                .orderId(refund.getOrder().getId())
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .cashierName(refund.getCashier().getFullName())
                .branchId(refund.getBranch().getId())
                .shiftReportId(refund.getShiftReport().getId())
                .createdAt(refund.getCreatedAt())

                .build();
    }
}
