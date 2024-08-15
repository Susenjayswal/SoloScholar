package com.soloscholar.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.soloscholar.entity.Role;
import com.soloscholar.entity.User;
import com.soloscholar.exception.NotFoundException;
import com.soloscholar.repository.UserRepository;
import com.soloscholar.service.UserService;



@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		var user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	//@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<User> findAllusers() {
		return userRepository.findAll();
	}

	//@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public User findUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("User not found with ID %d", id)));
	}

	@Override
	public void createUser(User user) {
		userRepository.save(user);
	}

	

	@Override
	public void deleteUser(Long id) {
		var User = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("User not found with ID %d", id)));

		userRepository.deleteById(User.getId());
	}


	

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public Page<User> findPaginated(Pageable pageable) {
		var pageSize = pageable.getPageSize();
		var currentPage = pageable.getPageNumber();
		var startItem = currentPage * pageSize;
		List<User> list;

		if (findAllusers().size() < startItem) {
			list = Collections.emptyList();
		} else {
			var toIndex = Math.min(startItem + pageSize, findAllusers().size());
			list = findAllusers().subList(startItem, toIndex);
		}

		return new PageImpl<User>(list, PageRequest.of(currentPage, pageSize), findAllusers().size());
	
	}


}
