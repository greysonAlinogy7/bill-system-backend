package com.billing.billing.system.service;

import com.billing.billing.system.model.Customer;
import com.billing.billing.system.payload.dto.CategoryDTO;
import com.billing.billing.system.repository.CustomerRepository;
import com.billing.billing.system.service.impl.ICategoryService;
import com.billing.billing.system.service.impl.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private  final CustomerRepository customerRepository;


    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) throws Exception {
        Customer customer1 = customerRepository.findById(id).orElseThrow(() -> new Exception("customer not found"));
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {

    }

    @Override
    public Customer getCustomer(Long id) {
        return null;
    }

    @Override
    public List<Customer> grtAllsCustomers() {
        return List.of();
    }

    @Override
    public List<Customer> searchCustomer(String keyword) {
        return List.of();
    }
}
