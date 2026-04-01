package com.billing.billing.system.controller;


import com.billing.billing.system.model.Customer;
import com.billing.billing.system.payload.response.ApiResponse;
import com.billing.billing.system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) throws Exception {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) throws Exception {
        customerService.deleteCustomer(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("customer deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.grtAllsCustomers());
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> searchCustomer(@RequestParam String keyword){
        return ResponseEntity.ok(customerService.searchCustomer(keyword));
    }


}
