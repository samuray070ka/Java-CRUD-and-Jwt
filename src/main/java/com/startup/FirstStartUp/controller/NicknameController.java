package com.startup.FirstStartUp.controller;

import com.startup.FirstStartUp.model.Nickname;
import com.startup.FirstStartUp.service.NicknameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nicknames")
@CrossOrigin(origins = "http://localhost:3000")
public class NicknameController {
    private final NicknameService nicknameService;

    public NicknameController(NicknameService nicknameService) {
        this.nicknameService = nicknameService;
    }

    @GetMapping
    public List<Nickname> getAllNicknames() {
        return nicknameService.getAllNicknames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nickname> getNicknameById(@PathVariable Long id) {
        return nicknameService.getNicknameById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Nickname createNickname(@RequestBody Nickname nickname) {
        return nicknameService.createNickname(nickname);
    }

    @PutMapping("/{id}")
    public Nickname updateNickname(@PathVariable Long id, @RequestBody Nickname nickname) {
        return nicknameService.updateNickname(id, nickname);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNickname(@PathVariable Long id) {
        nicknameService.deleteNickname(id);
        return ResponseEntity.noContent().build();
    }
}