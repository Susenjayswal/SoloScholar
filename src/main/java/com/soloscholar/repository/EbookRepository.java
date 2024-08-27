package com.soloscholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soloscholar.entity.Ebook;

public interface EbookRepository extends JpaRepository<Ebook, Long> {
}


