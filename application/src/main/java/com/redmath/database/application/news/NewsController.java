package com.redmath.database.application.news;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/News")
public class NewsController {

    private final NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Map<String, List<News>>> findAll (){
        List<News> news = service.findAll();
        if (news.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("content", news));
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> findById(@PathVariable("id") Long id) {
        News news = service.findById(id);
        if (news == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(news);
    }



    @PostMapping
    public ResponseEntity<News> create(@RequestBody News news) {
        News created = service.create(news);
        if (created != null) {
            return ResponseEntity.ok(created);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<News> update(@PathVariable("id") long id, @RequestBody News updatedNews) {
//        Optional<News> existingNews = service.NewsRepository.findById(id);
//
//        if (existingNews.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        News newsToUpdate = existingNews.get();
//        newsToUpdate.setTitle(updatedNews.getTitle());
//
//        News updated = service.update(newsToUpdate);
//
//        if (updated != null) {
//            return ResponseEntity.ok(updated);
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT).build();
//    }
//
//
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        News news = service.findById(id);

        if (news==null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
