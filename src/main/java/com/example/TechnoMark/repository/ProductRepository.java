package com.example.TechnoMark.repository;

import com.example.TechnoMark.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
