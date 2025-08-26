package com.startup.FirstStartUp.controller;

import com.startup.FirstStartUp.model.News;
import com.startup.FirstStartUp.repository.NewsRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "http://localhost:3000")
public class NewsController {
    private final NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    // ✅ Barcha newslarni olish
    @GetMapping
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    // ✅ Slug bo‘yicha bitta news
    @GetMapping("/{slug}")
    public News getNewsBySlug(@PathVariable String slug) {
        return newsRepository.findBySlug(slug).orElseThrow();
    }

    // ✅ Yangi qo‘shish (frontenddan POST bilan yuboriladi)
    @PostMapping
    public News createNews(@RequestBody News news) {
        return newsRepository.save(news);
    }

    // ✅ Yangilash
    @PutMapping("/{id}")
    public News updateNews(@PathVariable Long id, @RequestBody News updated) {
        News news = newsRepository.findById(id).orElseThrow();
        news.setTitle(updated.getTitle());
        news.setImageUrl(updated.getImageUrl());
        news.setSlug(updated.getSlug());
        news.setCategory(updated.getCategory());
        return newsRepository.save(news);
    }

    // ✅ O‘chirish
    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Long id) {
        newsRepository.deleteById(id);
    }

    // ✅ Filter: 1 kunlik yangiliklar
    @GetMapping("/today")
    public List<News> getTodayNews() {
        LocalDateTime yesterday = LocalDateTime.now().minus(1, ChronoUnit.DAYS);
        return newsRepository.findByCreatedAtAfter(yesterday);
    }

    // ✅ Filter: category bo‘yicha
    @GetMapping("/category/{category}")
    public List<News> getByCategory(@PathVariable String category) {
        return newsRepository.findByCategory(category);
    }
}