package com.soloscholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soloscholar.entity.Reader;




@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
	Reader findByEmail(String email);
}