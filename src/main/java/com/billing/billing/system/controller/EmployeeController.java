package com.billing.billing.system.controller;

import com.billing.billing.system.domain.UserRole;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.UserDTO;
import com.billing.billing.system.payload.response.ApiResponse;
import com.billing.billing.system.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private  final EmployeeService employeeService;


    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDTO> createStoreEmployee(@PathVariable Long storeId, @RequestBody UserDTO userDTO) throws Exception {
        UserDTO employee = employeeService.createStoreEmployee(userDTO, storeId);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/branch/{id}")
    public ResponseEntity<UserDTO> createBranchEmployee(@PathVariable Long id, @RequestBody UserDTO userDTO) throws Exception {
        UserDTO employee = employeeService.createBranchEmployee(userDTO, id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/branch/{branchId}")
    public ResponseEntity<UserDTO> updateEmployee(@PathVariable Long branchId, @RequestBody UserDTO userDTO) throws Exception {
        UserDTO employee = employeeService.updateEmployee(branchId, userDTO);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/branch/{branchId}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id) throws Exception {
        employeeService.deleteEmployee(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("user successfully deleted");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<UserDTO>> storeEmployee(@PathVariable Long id, @RequestParam(required = false)UserRole role) throws Exception {
       List<UserDTO> employee = employeeService.findStoreEmployees(id, role);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<UserDTO>> branchEmployee(@PathVariable Long id, @RequestParam(required = false)UserRole role) throws Exception {
        List<UserDTO> employee = employeeService.findBranchEmployees(id, role);
        return ResponseEntity.ok(employee);
    }
}
