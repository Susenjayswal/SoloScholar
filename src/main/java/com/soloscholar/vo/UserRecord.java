package com.soloscholar.vo;

import java.util.Collection;

import com.soloscholar.entity.Role;

public record UserRecord(Long id, String firstName, String lastName, String address, String email, int age, Long phone, String gender, String idtype, String idnum, Role roles, String password) {

	}