package com.billing.billing.system.service;

import com.billing.billing.system.model.Refund;
import com.billing.billing.system.payload.dto.RefundDTO;
import com.billing.billing.system.repository.RefundRepository;
import com.billing.billing.system.service.impl.IRefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RefundService implements IRefundService {
    private  final RefundRepository refundRepository;
    private  final UserService userService;


    @Override
    public RefundDTO createRefund(Refund refund) {
        return null;
    }

    @Override
    public List<RefundDTO> getAllRefunds() {
        return List.of();
    }

    @Override
    public RefundDTO getRefundByCashier(Long cahierId) {
        return null;
    }

    @Override
    public RefundDTO getRefundByShiftReport(Long shiftReportId) {
        return null;
    }

    @Override
    public List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) {
        return List.of();
    }

    @Override
    public List<RefundDTO> getRefundByBranch(Long branchId) {
        return List.of();
    }

    @Override
    public RefundDTO getRefundById(Long refundId) {
        return null;
    }

    @Override
    public void deleteRefund(Long refundId) {

    }
}
