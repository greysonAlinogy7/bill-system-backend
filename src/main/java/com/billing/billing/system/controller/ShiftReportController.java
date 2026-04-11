package com.billing.billing.system.controller;

import com.billing.billing.system.payload.dto.ShiftReportDTO;
import com.billing.billing.system.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shift-report")
public class ShiftReportController {
    private final ShiftService shiftService;


    @PostMapping("/start")
    ResponseEntity<ShiftReportDTO> startShift() throws Exception {
        return ResponseEntity.ok(shiftService.startShift());
    }

    @PatchMapping ("/start")
    ResponseEntity<ShiftReportDTO> endShift() throws Exception {
        return ResponseEntity.ok(shiftService.endShift(null, null));
    }

    @GetMapping("/current")
    ResponseEntity<ShiftReportDTO> getCurrentShiftProgress() throws Exception {
        return ResponseEntity.ok(shiftService.getCurrentShiftProgress(null));
    }

    @GetMapping("/cashier/{cashierId}/by-date")
    ResponseEntity<ShiftReportDTO> getShiftReportByDate(@PathVariable Long cahierId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime date) throws Exception {
        return ResponseEntity.ok(shiftService.getShiftByCashierAndDate(cahierId, date));
    }

    @GetMapping("/cashier/{cashierId}")
    ResponseEntity<List<ShiftReportDTO>> getShiftReportByCashier(@PathVariable Long cahierId) throws Exception {
        return ResponseEntity.ok(shiftService.getShiftReportByCashierId(cahierId));
    }

    @GetMapping("/branch/{branchId}")
    ResponseEntity<List<ShiftReportDTO>> getShiftReportByBranch(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(shiftService.getShiftReportByBranchId(branchId));
    }

    @GetMapping("/{id}")
    ResponseEntity<ShiftReportDTO> getShiftReportById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(shiftService.getShiftReportById(id));
    }


}
