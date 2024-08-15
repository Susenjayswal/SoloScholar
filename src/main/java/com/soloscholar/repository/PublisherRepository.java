package com.soloscholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soloscholar.entity.Publisher;



public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
