package com.soloscholar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloscholar.entity.Ebook;
import com.soloscholar.repository.EbookRepository;

@Service
public class EbookService {

    @Autowired
    private EbookRepository ebookRepository;

    public List<Ebook> getAllEbooks() {
        return ebookRepository.findAll();
    }

    public void saveBook(Ebook ebook) {
        ebookRepository.save(ebook);
    }

    public Ebook getEbookById(Long id) {
        return ebookRepository.findById(id).orElse(null);
    }

    public void deleteEbookById(Long id) {
        ebookRepository.deleteById(id);
    }

	public void updateEbook(Ebook ebook) {
		ebookRepository.save(ebook);
		
	}

	
}
