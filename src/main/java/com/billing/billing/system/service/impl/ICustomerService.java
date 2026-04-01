package com.billing.billing.system.service.impl;

import com.billing.billing.system.model.Customer;

import java.util.List;

public interface ICustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer) throws Exception;
    void deleteCustomer(Long id) throws Exception;
    Customer getCustomer(Long id) throws Exception;
    List<Customer> grtAllsCustomers();
    List<Customer> searchCustomer(String keyword);
}
