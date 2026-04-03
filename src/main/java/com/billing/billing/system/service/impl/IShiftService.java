package com.billing.billing.system.service.impl;


import com.billing.billing.system.exception.UserException;
import com.billing.billing.system.payload.dto.ShiftReportDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IShiftService {
    ShiftReportDTO startShift(Long cashierId, Long branchId, LocalDateTime shiftStart) throws Exception;
    ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception;
    ShiftReportDTO getShiftReportById(Long id);
    List<ShiftReportDTO> getAllShiftReports();
    List<ShiftReportDTO> getShiftReportByCashierId(Long cashierId);
    List<ShiftReportDTO> getShiftReportByBranchId(Long branchId);
    ShiftReportDTO getCurrentShiftProgress(Long cashierId);
    ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date);


}
