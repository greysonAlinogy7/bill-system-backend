package com.billing.billing.system.service.impl;

import com.billing.billing.system.domain.UserRole;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.UserDTO;

import java.util.List;

public interface IEmployeeService {
    UserDTO createStoreEmployee(UserDTO employee, Long storeId) throws Exception;
    UserDTO createBranchEmployee(UserDTO employee, Long branchId) throws Exception;
    UserDTO updateEmployee(Long employeeId, UserDTO employeeDetails) throws Exception;
    void deleteEmployee(Long employeeId) throws Exception;
    List<UserDTO> findStoreEmployees(Long storeId, UserRole role) throws Exception;
    List<UserDTO> findBranchEmployees(Long branchId, UserRole role) throws Exception;

}
