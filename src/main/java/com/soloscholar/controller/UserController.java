package com.soloscholar.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.soloscholar.entity.User;
import com.soloscholar.service.UserService;


@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public String findAllAuthors(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		var currentPage = page.orElse(1);
		var pageSize = size.orElse(5);
		var userPage = userService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("users", userPage);

		int totalPages = userPage.getTotalPages();
		if (totalPages > 0) {
			var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "list-users";
	}

	@PostMapping("/user/{id}")
	public String findAuthorById(@PathVariable("id") Long id, Model model) {

		model.addAttribute("user", userService.findUserById(id));
		return "list-user";
	}

	@GetMapping("/addUser")
	public String showCreateForm(User user) {
		return "add-user";
	}

	@PostMapping("/add-user")
	public String createAuthor(User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-user";
		}

		userService.createUser(user);
		model.addAttribute("user", userService.findAllusers());
		return "redirect:/users";
	}

	@GetMapping("/updateUser/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		model.addAttribute("user", userService.findUserById(id));
		return "update-user";
	}

	@PostMapping("/update-user/{id}")
	public String updateUser(@PathVariable("id") Long id, User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "update-user";
		}

		userService.updateUser(user);
		model.addAttribute("user", userService.findAllusers());
		return "redirect:/users";
	}

	@RequestMapping("/removeUser/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		userService.deleteUser(id);

		model.addAttribute("user", userService.findAllusers());
		return "redirect:/users";
	}

}
