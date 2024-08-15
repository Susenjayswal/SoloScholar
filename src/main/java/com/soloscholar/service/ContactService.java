package com.soloscholar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.soloscholar.entity.Contact;


public interface ContactService {

	public List<Contact> findAllContact();

	public Contact findContactById(Long id);

	public void createContact(Contact contact);

	public void updateContact(Contact contact);

	public void deleteContact(Long id);

	public Page<Contact> findPaginated(Pageable pageable);

}