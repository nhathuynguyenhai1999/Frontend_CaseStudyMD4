package com.example.casestudylibrary.repository;

import com.example.casestudylibrary.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepository extends JpaRepository<Image, Long> {
}
