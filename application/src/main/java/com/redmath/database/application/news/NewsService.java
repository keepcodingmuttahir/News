package com.redmath.database.application.news;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

@Service
public class NewsService extends Exception{
    private final NewsRepository repository;
    Logger logger = LoggerFactory.getLogger(getClass());

    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }
    public List<News> findAll() {
        return repository.findAll();
    }

    public News findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public News create(News news) {
//        news.setId();

        if (news.getId() != null && repository.existsById(news.getId())) {
            return null;
        }
        news.setId(System.currentTimeMillis());
        news.setReportedAt(LocalDateTime.now());
        return repository.save(news);
    }

    public News update(News newsToUpdate, long id) {

        Optional<News> existingNewsOptional = repository.findById(id);

        if (existingNewsOptional.isEmpty()) {
            return null;
        }
        News existingNews = existingNewsOptional.get();
        existingNews.setTitle(newsToUpdate.getTitle());
        existingNews.setDetails(newsToUpdate.getDetails());
        existingNews.setTags(newsToUpdate.getTags());
        existingNews.setReportedAt(newsToUpdate.getReportedAt());
        repository.save(existingNews);
        return existingNews;
    }

    public void delete(long id) {
        Optional<News> news = repository.findById(id);

        if (news.isPresent()) {
            repository.deleteById(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found");
        }
    }
}

