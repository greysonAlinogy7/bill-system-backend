package com.billing.billing.system.controller;

import com.billing.billing.system.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shift-report")
public class ShiftReportController {
    private final ShiftService shiftService;
}
