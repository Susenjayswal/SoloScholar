package com.soloscholar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.soloscholar.entity.Expenditure;

public interface ExpenditureService {

	public List<Expenditure> findAllExpenditure();

	public Expenditure findExpenditureById(Long id);

	public void createExpenditure(Expenditure expenditure);

	public void updateExpenditure(Expenditure expenditure);

	public void deleteExpenditure(Long id);

	public Page<Expenditure> findPaginated(Pageable pageable);

}