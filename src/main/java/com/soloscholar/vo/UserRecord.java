package com.soloscholar.vo;

public record UserRecord(Long id, String firstName, String lastName, String address, String email, int age, Long phone, String gender, String idtype, String idnum,String roles, String password) {

	}