package com.infychat.contactcentermanagement.controller;

import com.example.contactcentermanagement.entity.Customer;
import com.example.contactcentermanagement.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;
    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        customer = new Customer("Jane", "Doe", "NY", LocalDate.of(1990, 2, 2), "CUST123");
        customer.setId(1L);
    }

    @Test
    void testGetAllCustomers() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(Collections.singletonList(customer));

        mockMvc.perform(get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(customer.getFirstName()));
    }

    @Test
    void testGetCustomerById() throws Exception {
        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/api/v1/customers/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(customer.getFirstName()));
    }

    @Test
    void testGetCustomerByCustomerId() throws Exception {
        when(customerService.getCustomerByCustomerId("CUST123")).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/api/v1/customers/customerId/{customerId}", "CUST123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(customer.getCustomerId()));
    }

    @Test
    void testCreateCustomer() throws Exception {
        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"location\":\"NY\",\"dateOfBirth\":\"1990-02-02\",\"customerId\":\"CUST123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(customer.getFirstName()));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        when(customerService.updateCustomer(eq(1L), any(Customer.class))).thenReturn(customer);

        mockMvc.perform(put("/api/v1/customers/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"location\":\"NY\",\"dateOfBirth\":\"1990-02-02\",\"customerId\":\"CUST123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(customer.getFirstName()));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        doNothing().when(customerService).deleteCustomer(1L);

        mockMvc.perform(delete("/api/v1/customers/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
