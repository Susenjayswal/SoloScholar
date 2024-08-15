package com.soloscholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soloscholar.entity.Contact;



public interface ContactRepository extends JpaRepository<Contact, Long> {

}
