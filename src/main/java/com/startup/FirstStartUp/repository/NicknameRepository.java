package com.startup.FirstStartUp.repository;

import com.startup.FirstStartUp.model.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NicknameRepository extends JpaRepository<Nickname, Long> {
}