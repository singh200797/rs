package com.infychat.contactcentermanagement.service;

import com.infychat.contactcentermanagement.entity.Agent;
import com.example.contactcentermanagement.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Cacheable(value = "agents", key = "#id")
    public Optional<Agent> getAgentById(Long id) {
        return agentRepository.findById(id);
    }

    @Cacheable(value = "agents", key = "#employeeId")
    public Optional<Agent> getAgentByEmployeeId(String employeeId) {
        return agentRepository.findByEmployeeId(employeeId);
    }

    @CacheEvict(value = "agents", allEntries = true)
    public Agent createAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @CacheEvict(value = "agents", key = "#id")
    public Agent updateAgent(Long id, Agent agentDetails) {
        return agentRepository.findById(id).map(agent -> {
            agent.setFirstName(agentDetails.getFirstName());
            agent.setLastName(agentDetails.getLastName());
            agent.setEmployeeId(agentDetails.getEmployeeId());
            agent.setDateOfJoining(agentDetails.getDateOfJoining());
            agent.setDateOfBirth(agentDetails.getDateOfBirth());
            return agentRepository.save(agent);
        }).orElseThrow(() -> new RuntimeException("Agent not found with id " + id));
    }

    @CacheEvict(value = "agents", key = "#id")
    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

}
