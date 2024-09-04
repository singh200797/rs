package com.infychat.contactcentermanagement.service;

import com.example.contactcentermanagement.entity.Agent;
import com.example.contactcentermanagement.repository.AgentRepository;
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

class AgentServiceTest {

    @Mock
    private AgentRepository agentRepository;

    @InjectMocks
    private AgentService agentService;

    private Agent agent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        agent = new Agent("John", "Doe", "EMP123", LocalDate.now(), LocalDate.of(1990, 1, 1));
        agent.setId(1L);  // Corrected syntax
    }

    @Test
    void testGetAgentById() {
        when(agentRepository.findById(1L)).thenReturn(Optional.of(agent));
        Optional<Agent> foundAgent = agentService.getAgentById(1L);
        assertTrue(foundAgent.isPresent());
        assertEquals(agent.getFirstName(), foundAgent.get().getFirstName());
    }

    @Test
    void testGetAgentByEmployeeId() {
        when(agentRepository.findByEmployeeId("EMP123")).thenReturn(Optional.of(agent));
        Optional<Agent> foundAgent = agentService.getAgentByEmployeeId("EMP123");
        assertTrue(foundAgent.isPresent());
        assertEquals(agent.getEmployeeId(), foundAgent.get().getEmployeeId());
    }

    @Test
    void testCreateAgent() {
        when(agentRepository.save(any(Agent.class))).thenReturn(agent);
        Agent createdAgent = agentService.createAgent(agent);
        assertNotNull(createdAgent);
        assertEquals("John", createdAgent.getFirstName());
    }

    @Test
    void testUpdateAgent() {
        when(agentRepository.findById(1L)).thenReturn(Optional.of(agent));
        when(agentRepository.save(any(Agent.class))).thenReturn(agent);

        Agent updatedAgent = agentService.updateAgent(1L, agent);
        assertEquals(agent.getId(), updatedAgent.getId());
    }

    @Test
    void testDeleteAgent() {
        doNothing().when(agentRepository).deleteById(1L);
        agentService.deleteAgent(1L);
        verify(agentRepository, times(1)).deleteById(1L);
    }
}
