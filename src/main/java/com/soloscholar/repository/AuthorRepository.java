package com.soloscholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soloscholar.entity.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {

}
