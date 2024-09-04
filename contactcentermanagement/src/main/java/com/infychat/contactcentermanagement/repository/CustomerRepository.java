package com.infychat.contactcentermanagement.repository;

import com.infychat.contactcentermanagement.entity.Customer;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface CustomerRepository extends Neo4jRepository<Customer, Long> {
    Optional<Customer> findByCustomerId(String customerId);
}
