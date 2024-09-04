package com.infychat.contactcentermanagement.controller;

import com.example.contactcentermanagement.entity.Agent;
import com.example.contactcentermanagement.service.AgentService;
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

class AgentControllerTest {

    @Mock
    private AgentService agentService;

    @InjectMocks
    private AgentController agentController;

    private MockMvc mockMvc;
    private Agent agent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(agentController).build();
        agent = new Agent("John", "Doe", "EMP123", LocalDate.now(), LocalDate.of(1990, 1, 1));
        agent.setId(1L);
    }

    @Test
    void testGetAllAgents() throws Exception {
        when(agentService.getAllAgents()).thenReturn(Collections.singletonList(agent));

        mockMvc.perform(get("/api/v1/agents")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(agent.getFirstName()));
    }

    @Test
    void testGetAgentById() throws Exception {
        when(agentService.getAgentById(1L)).thenReturn(Optional.of(agent));

        mockMvc.perform(get("/api/v1/agents/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(agent.getFirstName()));
    }

    @Test
    void testGetAgentByEmployeeId() throws Exception {
        when(agentService.getAgentByEmployeeId("EMP123")).thenReturn(Optional.of(agent));

        mockMvc.perform(get("/api/v1/agents/employeeId/{employeeId}", "EMP123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(agent.getEmployeeId()));
    }

    @Test
    void testCreateAgent() throws Exception {
        when(agentService.createAgent(any(Agent.class))).thenReturn(agent);

        mockMvc.perform(post("/api/v1/agents")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"employeeId\":\"EMP123\",\"dateOfJoining\":\"2023-01-01\",\"dateOfBirth\":\"1990-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(agent.getFirstName()));
    }

    @Test
    void testUpdateAgent() throws Exception {
        when(agentService.updateAgent(eq(1L), any(Agent.class))).thenReturn(agent);

        mockMvc.perform(put("/api/v1/agents/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"employeeId\":\"EMP123\",\"dateOfJoining\":\"2023-01-01\",\"dateOfBirth\":\"1990-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(agent.getFirstName()));
    }

    @Test
    void testDeleteAgent() throws Exception {
        doNothing().when(agentService).deleteAgent(1L);

        mockMvc.perform(delete("/api/v1/agents/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}