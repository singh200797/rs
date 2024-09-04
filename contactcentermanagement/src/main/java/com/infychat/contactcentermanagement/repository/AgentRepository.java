package com.infychat.contactcentermanagement.repository;

import com.infychat.contactcentermanagement.entity.Agent;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import java.util.Optional;

public interface AgentRepository extends Neo4jRepository<Agent, Long> {
    Optional<Agent> findByEmployeeId(String employeeId);
}
