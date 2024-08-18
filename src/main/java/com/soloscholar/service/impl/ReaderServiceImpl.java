package com.soloscholar.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.soloscholar.entity.Reader;
import com.soloscholar.exception.NotFoundException;
import com.soloscholar.repository.ReaderRepository;
import com.soloscholar.service.ReaderService;



@Service
public class ReaderServiceImpl implements ReaderService {

	private final ReaderRepository readerRepository;

	public ReaderServiceImpl(ReaderRepository readerRepository) {
		this.readerRepository = readerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		var user = readerRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String roles) {
	    return Arrays.stream(roles.split(","))
	            .map(role -> new SimpleGrantedAuthority(role.trim()))
	            .collect(Collectors.toList());
	
	}



	//@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Reader> findAllReaders() {
		return readerRepository.findAll();
	}

	//@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Reader findReaderById(Long id) {
		return readerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Reader not found with ID %d", id)));
	}

	@Override
	public void createReader(Reader reader) {
		readerRepository.save(reader);
	}

	

	@Override
	public void deleteReader(Long id) {
		var Reader = readerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Reader not found with ID %d", id)));

		readerRepository.deleteById(Reader.getId());
	}

	 public Reader findReaderByEmail(String email) {
	        return readerRepository.findByEmail(email);
	    }
	

	@Override
	public void updateReader(Reader reader) {
		readerRepository.save(reader);
		
	}

	@Override
	public Page<Reader> findPaginated(Pageable pageable) {
		var pageSize = pageable.getPageSize();
		var currentPage = pageable.getPageNumber();
		var startItem = currentPage * pageSize;
		List<Reader> list;

		if (findAllReaders().size() < startItem) {
			list = Collections.emptyList();
		} else {
			var toIndex = Math.min(startItem + pageSize, findAllReaders().size());
			list = findAllReaders().subList(startItem, toIndex);
		}

		return new PageImpl<Reader>(list, PageRequest.of(currentPage, pageSize), findAllReaders().size());
	
	}


}
