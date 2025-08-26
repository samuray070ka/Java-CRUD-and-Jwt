package com.startup.FirstStartUp.service;

import com.startup.FirstStartUp.model.Nickname;
import com.startup.FirstStartUp.repository.NicknameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NicknameService {
    private final NicknameRepository nicknameRepository;

    public NicknameService(NicknameRepository nicknameRepository) {
        this.nicknameRepository = nicknameRepository;
    }

    public List<Nickname> getAllNicknames() {
        return nicknameRepository.findAll();
    }

    public Optional<Nickname> getNicknameById(Long id) {
        return nicknameRepository.findById(id);
    }

    public Nickname createNickname(Nickname nickname) {
        return nicknameRepository.save(nickname);
    }

    public Nickname updateNickname(Long id, Nickname nickname) {
        nickname.setId(id);
        return nicknameRepository.save(nickname);
    }

    public void deleteNickname(Long id) {
        nicknameRepository.deleteById(id);
    }
}