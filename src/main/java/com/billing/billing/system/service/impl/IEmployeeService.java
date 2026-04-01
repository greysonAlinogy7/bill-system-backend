package com.billing.billing.system.service.impl;

import com.billing.billing.system.domain.UserRole;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.UserDTO;

import java.util.List;

public interface IEmployeeService {
    UserDTO createStoreEmployee(UserDTO employee, Long storeId) throws Exception;
    UserDTO createBranchEmployee(UserDTO employee, Long branchId);
    User updateEmployee(Long employeeId, User employeeDetails);
    void deleteEmployee(Long employeeId);
    List<User> findStoreEmployees(Long storeId, UserRole role);
    List<User> findBranchEmployees(Long branchId, UserRole role);

}
