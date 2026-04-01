package com.billing.billing.system.service.impl;

import com.billing.billing.system.domain.OrderStatus;
import com.billing.billing.system.domain.PaymentType;
import com.billing.billing.system.exception.UserException;
import com.billing.billing.system.payload.dto.OrderDTO;

import java.util.List;

public interface IOrderService {
    OrderDTO createOrder(OrderDTO orderDTO) throws Exception;
    OrderDTO getOrderById(Long id) throws Exception;
    List<OrderDTO> getOrdersByBranch(Long branchId, Long customerId, Long cahierId, PaymentType paymentType, OrderStatus status);
    List<OrderDTO> getOrderByCashier(Long cashierId);
    void deleteOrder(Long id) throws Exception;
    List<OrderDTO> getTodayOrdersByBranch(Long branchId);
    List<OrderDTO> getOrdersByCustomerId(Long customerId);
    List<OrderDTO> getTop5RecentOrdersByBranchId(Long branchId);
}
