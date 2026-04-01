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
import java.util.stream.Collectors;

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
    public UserDTO createBranchEmployee(UserDTO employee, Long branchId) throws Exception {
        Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new Exception("branch does not exist"));
        if (employee.getRole()==UserRole.ROLE_BRANCH_CASHIER || employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            User user =UserMapper.toEntity(employee);
            user.setBranch(branch);
            user.setPassword(passwordEncoder.encode(employee.getPassword()));
            return UserMapper.toDTO(userRepository.save(user));
        }
        throw  new Exception("branch role not supported");
    }

    @Override
    public UserDTO updateEmployee(Long employeeId, UserDTO employeeDetails) throws Exception {
        User existingEmployee =userRepository.findById(employeeId).orElseThrow(() -> new Exception("employee does not exist"));
        Branch branch = branchRepository.findById(employeeDetails.getBranchId()).orElseThrow(() -> new Exception("branch does not exist"));

        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setFullName(employeeDetails.getFullName());
        existingEmployee.setPassword(employeeDetails.getPassword());
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setBranch(branch);

        return UserMapper.toDTO(userRepository.save(existingEmployee));
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        User employee = userRepository.findById(employeeId).orElseThrow(() -> new Exception("employee does not found"));
        userRepository.delete(employee);

    }

    @Override
    public List<UserDTO> findStoreEmployees(Long storeId, UserRole role) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new Exception("store not found"));
        return userRepository.findByStore(store).stream().filter(user -> role==null || user.getRole()==role).map(UserMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findBranchEmployees(Long branchId, UserRole role) throws Exception {
        Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new Exception("branch not found"));
        return  userRepository.findByBranchId(branch.getId()).stream().filter(user -> role==null || user.getRole()==role).map(UserMapper::toDTO).collect(Collectors.toList());
    }
}
