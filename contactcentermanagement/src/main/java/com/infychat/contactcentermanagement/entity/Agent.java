package com.infychat.contactcentermanagement.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.time.LocalDate;

@Node
public class Agent {


    @Id
    @GeneratedValue
    private Long id;
    
    private String firstName;
    private String lastName;
    private String employeeId;
    private LocalDate dateOfJoining;
    private LocalDate dateOfBirth;


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

    public String getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDateOfJoining() {
        return this.dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Agent() {}

    public Agent(String firstName, String lastName, String employeeId, LocalDate dateOfJoining, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.dateOfJoining = dateOfJoining;
        this.dateOfBirth = dateOfBirth;
    }

}

}