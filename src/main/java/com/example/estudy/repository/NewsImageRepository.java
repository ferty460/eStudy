package com.example.estudy.repository;

import com.example.estudy.domain.news.NewsImage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NewsImageRepository extends JpaRepository<NewsImage, Long> {
}
