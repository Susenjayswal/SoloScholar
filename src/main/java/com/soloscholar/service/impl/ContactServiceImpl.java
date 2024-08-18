package com.soloscholar.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.soloscholar.entity.Contact;
import com.soloscholar.exception.NotFoundException;
import com.soloscholar.repository.ContactRepository;
import com.soloscholar.service.ContactService;




@Service
public class ContactServiceImpl implements ContactService {

	private final ContactRepository contactRepository;

	public ContactServiceImpl(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Contact> findAllContact() {
		return contactRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Contact findContactById(Long id) {
		return contactRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Contact not found with ID %d", id)));
	}

	@Override
	public void createContact(Contact contact) {
		contactRepository.save(contact);
	}

	@Override
	public void updateContact(Contact contact) {
		contactRepository.save(contact);
	}

	@Override
	public void deleteContact(Long id) {
		var contact = contactRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Contact not found with ID %d", id)));

		contactRepository.deleteById(contact.getId());
	}

	@Override
	public Page<Contact> findPaginated(Pageable pageable) {

		var pageSize = pageable.getPageSize();
		var currentPage = pageable.getPageNumber();
		var startItem = currentPage * pageSize;
		List<Contact> list;

		if (findAllContact().size() < startItem) {
			list = Collections.emptyList();
		} else {
			var toIndex = Math.min(startItem + pageSize, findAllContact().size());
			list = findAllContact().subList(startItem, toIndex);
		}

		return new PageImpl<Contact>(list, PageRequest.of(currentPage, pageSize), findAllContact().size());

	}

}
