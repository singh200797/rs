package com.infychat.contactcentermanagement.service;

import com.infychat.contactcentermanagement.entity.Customer;
import com.example.contactcentermanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Cacheable(value = "customers", key = "#id")
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Cacheable(value = "customers", key = "#customerId")
    public Optional<Customer> getCustomerByCustomerId(String customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    @CacheEvict(value = "customers", allEntries = true)
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @CacheEvict(value = "customers", key = "#id")
    public Customer updateCustomer(Long id, Customer customerDetails) {
        return customerRepository.findById(id).map(customer -> {
            customer.setFirstName(customerDetails.getFirstName());
            customer.setLastName(customerDetails.getLastName());
            customer.setLocation(customerDetails.getLocation());
            customer.setDateOfBirth(customerDetails.getDateOfBirth());
            customer.setCustomerId(customerDetails.getCustomerId());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    @CacheEvict(value = "customers", key = "#id")
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
