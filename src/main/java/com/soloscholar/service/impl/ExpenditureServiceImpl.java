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

import com.soloscholar.entity.Expenditure;
import com.soloscholar.exception.NotFoundException;
import com.soloscholar.repository.ExpenditureRepository;
import com.soloscholar.service.ExpenditureService;



@Service
public class ExpenditureServiceImpl implements ExpenditureService {

	private final ExpenditureRepository expenditureRepository;

	public ExpenditureServiceImpl(ExpenditureRepository expenditureRepository) {
		this.expenditureRepository = expenditureRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Expenditure> findAllExpenditure() {
		return expenditureRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Expenditure findExpenditureById(Long id) {
		return expenditureRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("expenditure not found with ID %d", id)));
	}

	@Override
	public void createExpenditure(Expenditure expenditure) {
		expenditureRepository.save(expenditure);
	}

	@Override
	public void updateExpenditure(Expenditure expenditure) {
		expenditureRepository.save(expenditure);
	}

	@Override
	public void deleteExpenditure(Long id) {
		var expenditure = expenditureRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Expenditure not found with ID %d", id)));

		expenditureRepository.deleteById(expenditure.getId());
	}

	@Override
	public Page<Expenditure> findPaginated(Pageable pageable) {

		var pageSize = pageable.getPageSize();
		var currentPage = pageable.getPageNumber();
		var startItem = currentPage * pageSize;
		List<Expenditure> list;

		if (findAllExpenditure().size() < startItem) {
			list = Collections.emptyList();
		} else {
			var toIndex = Math.min(startItem + pageSize, findAllExpenditure().size());
			list = findAllExpenditure().subList(startItem, toIndex);
		}

		return new PageImpl<Expenditure>(list, PageRequest.of(currentPage, pageSize), findAllExpenditure().size());

	}

}
