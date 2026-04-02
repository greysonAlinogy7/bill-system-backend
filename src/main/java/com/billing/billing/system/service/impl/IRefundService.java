package com.billing.billing.system.service.impl;

import com.billing.billing.system.model.Refund;
import com.billing.billing.system.payload.dto.RefundDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IRefundService {
    RefundDTO createRefund(Refund refund);
    List<RefundDTO> getAllRefunds();
    RefundDTO getRefundByCashier(Long cahierId);
    RefundDTO getRefundByShiftReport(Long shiftReportId);
    List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate);
    List<RefundDTO> getRefundByBranch(Long branchId);
    RefundDTO getRefundById(Long refundId);
    void deleteRefund(Long refundId);
}
