package com.soloscholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.soloscholar.entity.Expenditure;


public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {

}
