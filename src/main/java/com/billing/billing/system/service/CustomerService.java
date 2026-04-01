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
        Customer customerUpdate = customerRepository.findById(id).orElseThrow(() -> new Exception("customer not found"));
        customerUpdate.setFirstName(customer.getFirstName());
        customerUpdate.setEmail(customer.getEmail());
        customerUpdate.setPhone(customer.getPhone());
        return customerRepository.save(customerUpdate);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new Exception("customer not found"));
        customerRepository.delete(customer);

    }

    @Override
    public Customer getCustomer(Long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(() -> new Exception("customer not found"));
    }

    @Override
    public List<Customer> grtAllsCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomer(String keyword) {
        return customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
    }
}
