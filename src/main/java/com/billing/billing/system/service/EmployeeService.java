package com.billing.billing.system.service;

import com.billing.billing.system.domain.UserRole;
import com.billing.billing.system.mapper.UserMapper;
import com.billing.billing.system.model.Branch;
import com.billing.billing.system.model.Store;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.UserDTO;
import com.billing.billing.system.repository.BranchRepository;
import com.billing.billing.system.repository.StoreRepository;
import com.billing.billing.system.repository.UserRepository;
import com.billing.billing.system.service.impl.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private  final BranchRepository branchRepository;
    private  final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createStoreEmployee(UserDTO employee, Long storeId) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new Exception("store not found"));
        Branch branch=null;

        if (employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            if (employee.getBranchId()==null){
                throw  new Exception("branch id is require to create branch manager");
            }
            branch = branchRepository.findById(employee.getBranchId()).orElseThrow(() -> new Exception("branch not found"));
        }
        User user = UserMapper.toEntity(employee);
        user.setStore(store);
        user.setBranch(branch);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));

        User savedEmployee = userRepository.save(user);

        if (employee.getRole()==UserRole.ROLE_BRANCH_MANAGER && branch != null){
            branch.setManager(savedEmployee);
            branchRepository.save(branch);
        }
        return UserMapper.toDTO(savedEmployee);
    }

    @Override
    public UserDTO createBranchEmployee(UserDTO employee, Long branchId) {
        return null;
    }

    @Override
    public User updateEmployee(Long employeeId, User employeeDetails) {
        return null;
    }

    @Override
    public void deleteEmployee(Long employeeId) {

    }

    @Override
    public List<User> findStoreEmployees(Long storeId, UserRole role) {
        return List.of();
    }

    @Override
    public List<User> findBranchEmployees(Long branchId, UserRole role) {
        return List.of();
    }
}
