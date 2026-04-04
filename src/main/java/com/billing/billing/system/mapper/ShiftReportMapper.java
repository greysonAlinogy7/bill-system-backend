package com.billing.billing.system.mapper;

import com.billing.billing.system.model.Order;
import com.billing.billing.system.model.Product;
import com.billing.billing.system.model.Refund;
import com.billing.billing.system.model.ShiftReport;
import com.billing.billing.system.payload.dto.OrderDTO;
import com.billing.billing.system.payload.dto.ProductDTO;
import com.billing.billing.system.payload.dto.RefundDTO;
import com.billing.billing.system.payload.dto.ShiftReportDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {
    public static ShiftReportDTO toDTO(ShiftReport shiftReport){
        return ShiftReportDTO.builder()
                .id(shiftReport.getId())
                .shiftEnd(shiftReport.getShiftEnd())
                .shiftStart(shiftReport.getShiftStart())
                .totalSales(shiftReport.getTotalSales())
                .totalRefunds(shiftReport.getTotalRefunds())
                .netSales(shiftReport.getNetSales())
                .cashier(UserMapper.toDTO(shiftReport.getCashier()))
                .cashierId(shiftReport.getCashier().getId())
                .branchId(shiftReport.getBranch().getId())
                .recentOrders(mapOrders(shiftReport.getRecentOrders()))
                .topSellingProducts(mapProduct(shiftReport.getTopSellingProducts()))
                .refunds(mapRefunds(shiftReport.getRefunds()))
                .paymentSummaries(shiftReport.getPaymentSummaries())
                .build();

    }

    private static List<RefundDTO> mapRefunds(List<Refund> refunds) {
        if (refunds== null || refunds.isEmpty()){
            return null;
        }
        return refunds.stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    private static List<ProductDTO> mapProduct(List<Product> topSellingProducts) {
        if (topSellingProducts == null || topSellingProducts.isEmpty()){
            return null;
        }
        return topSellingProducts.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    private static List<OrderDTO> mapOrders(List<Order> recentOrders) {
        if (recentOrders == null || recentOrders.isEmpty()){
            return null;
        }
        return recentOrders.stream().map(OrderMapper::orderDTO).collect(Collectors.toList());
    }
}
