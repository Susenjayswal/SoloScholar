package com.soloscholar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;


import com.soloscholar.entity.User;

public interface UserService extends UserDetailsService {

	public List<User> findAllusers();

	public User findUserById(Long id);

	public void createUser(User user);

	public void updateUser(User user);

	public void deleteUser(Long id);

	public Page<User> findPaginated(Pageable pageable);

}