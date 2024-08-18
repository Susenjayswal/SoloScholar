package com.soloscholar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.soloscholar.entity.Reader;

public interface ReaderService extends UserDetailsService {

	public List<Reader> findAllReaders();

	public Reader findReaderById(Long id);

	public void createReader(Reader reader);

	public void updateReader(Reader reader);

	public void deleteReader(Long id);

	public Page<Reader> findPaginated(Pageable pageable);

	public Reader findReaderByEmail(String email);

}