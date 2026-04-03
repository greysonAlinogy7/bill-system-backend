package com.billing.billing.system.service.impl;

import com.billing.billing.system.exception.UserException;
import com.billing.billing.system.model.Refund;
import com.billing.billing.system.payload.dto.RefundDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IRefundService {
    RefundDTO createRefund(RefundDTO refund) throws Exception;
    List<RefundDTO> getAllRefunds();
    List<RefundDTO> getRefundByCashier(Long cahierId);
    List<RefundDTO> getRefundByShiftReport(Long shiftReportId);
    List<RefundDTO> getRefundByCashierIdAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate);
    List<RefundDTO> getRefundByBranch(Long branchId);
    RefundDTO getRefundById(Long refundId) throws Exception;
    void deleteRefund(Long refundId) throws Exception;
}
