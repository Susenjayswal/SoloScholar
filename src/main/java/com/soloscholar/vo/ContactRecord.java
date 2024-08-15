package com.soloscholar.vo;

import java.time.LocalDate;

public record ContactRecord (Long id, String name, String email, String message, LocalDate date, Long Phone) { 

}