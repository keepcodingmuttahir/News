package com.redmath.database.application.news;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

@Service
public class NewsService {
    private final NewsRepository repository;

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
        if (news.getId() != null && repository.existsById(news.getId())) {
            return null;
        }
        news.setId(System.currentTimeMillis());
        news.setReportedAt(LocalDateTime.now());
        return repository.save(news);
    }
//    public News update(News newsToUpdate) {
//        Optional<News> existingNews = NewsRepository.findById(newsToUpdate.getId());
//
//        if (existingNews.isPresent()) {
//            News updatedNews = existingNews.get();
//            updatedNews.setTitle(newsToUpdate.getTitle());
//            return NewsRepository.save(updatedNews);
//        } else {
//            throw new ChangeSetPersister.NotFoundException("News not found with ID: " + newsToUpdate.getId());
//        }
//    }
//
    public void delete(long id) {
        Optional<News> news = repository.findById(id);

        if (news.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found");
        }
    }
}

