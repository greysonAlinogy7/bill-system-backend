package com.billing.billing.system.controller;

import com.billing.billing.system.domain.OrderStatus;
import com.billing.billing.system.domain.PaymentType;
import com.billing.billing.system.payload.dto.OrderDTO;
import com.billing.billing.system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private  final OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDTO>> getOrderByBranch(
            @PathVariable Long branchId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long cashierId,
            @RequestParam(required = false)PaymentType paymentType,
            @RequestParam(required = false)OrderStatus status
            ) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByBranch(branchId, customerId, cashierId, paymentType, status));
    }

    @GetMapping("/cashier/{id}")
    public ResponseEntity<List<OrderDTO>> getOrderByCashier(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrderByCashier(id));
    }

    @GetMapping("/today/branch/{id}")
    public ResponseEntity<List<OrderDTO>> getTodayOrders(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getTodayOrdersByBranch(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDTO>> getCustomerOrder(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(id));
    }

    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDTO>> getRecentOrders(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchId(branchId));
    }

}
