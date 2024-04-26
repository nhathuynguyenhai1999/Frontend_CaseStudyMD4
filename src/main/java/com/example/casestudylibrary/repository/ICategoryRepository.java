package com.example.casestudylibrary.repository;

import com.example.casestudylibrary.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
