package com.billing.billing.system.repository;

import com.billing.billing.system.model.Order;
import com.billing.billing.system.model.User;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByBranchId(Long branchId);
    List<Order> findByCashierId(Long cashierId);
    List<Order> findByBranchIdAndCreatedAtBetween(Long branchId, LocalDateTime from, LocalDateTime to);
    List<Order> findByCashierAndCreatedAtBetween(User cashier, LocalDateTime from, LocalDateTime to);
    List<Order> findTop5ByBranchIdOrderByCreatedAtDesc(Long branchId);
}
