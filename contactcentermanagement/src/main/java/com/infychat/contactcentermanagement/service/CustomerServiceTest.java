package com.infychat.contactcentermanagement.service;

import com.example.contactcentermanagement.entity.Customer;
import com.example.contactcentermanagement.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer("Jane", "Doe", "NY", LocalDate.of(1990, 2, 2), "CUST123");
        customer.setId(1L);
    }

    @Test
    void testGetCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Optional<Customer> foundCustomer = customerService.getCustomerById(1L);
        assertTrue(foundCustomer.isPresent());
        assertEquals(customer.getFirstName(), foundCustomer.get().getFirstName());
    }

    @Test
    void testGetCustomerByCustomerId() {
        when(customerRepository.findByCustomerId("CUST123")).thenReturn(Optional.of(customer));
        Optional<Customer> foundCustomer = customerService.getCustomerByCustomerId("CUST123");
        assertTrue(foundCustomer.isPresent());
        assertEquals(customer.getCustomerId(), foundCustomer.get().getCustomerId());
    }

    @Test
    void testCreateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer createdCustomer = customerService.createCustomer(customer);
        assertNotNull(createdCustomer);
        assertEquals("Jane", createdCustomer.getFirstName());
    }

    @Test
    void testUpdateCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer updatedCustomer = customerService.updateCustomer(1L, customer);
        assertEquals(customer.getId(), updatedCustomer.getId());
    }

    @Test
    void testDeleteCustomer() {
        doNothing().when(customerRepository).deleteById(1L);
        customerService.deleteCustomer(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }
}