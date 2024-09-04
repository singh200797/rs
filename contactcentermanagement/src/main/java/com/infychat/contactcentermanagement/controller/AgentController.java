package com.infychat.contactcentermanagement.controller;

import com.infychat.contactcentermanagement.entity.Agent;
import com.infychat.contactcentermanagement.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/agents")

public class AgentController {


    @Autowired
    private AgentService agentService;

    @GetMapping
    public List<Agent> getAllAgents() {
        return agentService.getAllAgents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agent> getAgentById(@PathVariable Long id) {
        Optional<Agent> agent = agentService.getAgentById(id);
        return agent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/employeeId/{employeeId}")
    public ResponseEntity<Agent> getAgentByEmployeeId(@PathVariable String employeeId) {
        Optional<Agent> agent = agentService.getAgentByEmployeeId(employeeId);
        return agent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agent createAgent(@RequestBody Agent agent) {
        return agentService.createAgent(agent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agent> updateAgent(@PathVariable Long id, @RequestBody Agent agentDetails) {
        return ResponseEntity.ok(agentService.updateAgent(id, agentDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
        return ResponseEntity.noContent().build();
    }

}
