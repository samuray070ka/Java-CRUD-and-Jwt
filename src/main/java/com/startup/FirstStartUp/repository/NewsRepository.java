package com.startup.FirstStartUp.repository;

import com.startup.FirstStartUp.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findBySlug(String slug);

    List<News> findByCreatedAtAfter(LocalDateTime dateTime);

    List<News> findByCategory(String category);
}