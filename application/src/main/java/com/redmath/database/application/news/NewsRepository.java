package com.redmath.database.application.news;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    //List<News> findAll();
    //Long findById(Long id);

}

