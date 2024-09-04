package com.infychat.contactcentermanagement.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.time.LocalDate;

@Node

public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    
    private String firstName;
    private String lastName;
    private String location;
    private LocalDate dateOfBirth;
    private String customerId;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Customer() {}

    public Customer(String firstName, String lastName, String location, LocalDate dateOfBirth, String customerId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
        this.customerId = customerId;
    }


}
