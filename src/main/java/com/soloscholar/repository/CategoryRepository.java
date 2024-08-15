package com.soloscholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soloscholar.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
