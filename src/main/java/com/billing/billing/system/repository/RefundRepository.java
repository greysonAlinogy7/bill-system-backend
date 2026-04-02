package com.billing.billing.system.repository;

import com.billing.billing.system.model.Refund;
import com.billing.billing.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
    List<Refund> findByCashierAndCreatedAtBetween(User cashier, LocalDateTime from, LocalDateTime to);
    List<Refund> findByCashierId(Long cashierId);
    List<Refund> findByShiftReportId(Long id);
    List<Refund> findByBranchId(Long id);
}
