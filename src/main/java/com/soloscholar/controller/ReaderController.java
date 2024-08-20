package com.soloscholar.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.soloscholar.entity.Reader;
import com.soloscholar.entity.User;
import com.soloscholar.service.ReaderService;
import com.soloscholar.service.UserService;


@Controller
public class ReaderController {

	private final ReaderService readerService;

	public ReaderController(ReaderService readerService) {
		this.readerService = readerService;
	}

	@GetMapping("/reader-login")
    public String readerDashboard() {
        return "reader"; // Returns the view for reader dashboard
    }
	@GetMapping("/readers")
	public String findAllReaders(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		var currentPage = page.orElse(1);
		var pageSize = size.orElse(5);
		var readerPage = readerService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("readers", readerPage);

		int totalPages = readerPage.getTotalPages();
		if (totalPages > 0) {
			var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "list-readers";
	}

	 @GetMapping("/readers/current")
	    public String getCurrentReader(Model model) {
	        // Get the authenticated user
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();

	        // Fetch reader details using the email
	        Reader reader = readerService.findReaderByEmail(email);

	        // Redirect to readers/{id}
	        return "redirect:/readers/" + reader.getId();
	    }
	
	    
	     @GetMapping("/readers/{id}")
	     public String getReaderProfile(@PathVariable Long id, Model model) {
	         Reader reader = readerService.findReaderById(id);
	         if (reader != null) {
	             model.addAttribute("reader", reader);
	             return "list-reader"; // Thymeleaf template name
	         } else {
	             return "redirect:/error"; // Handle the case where the reader is not found
	         }
	     }


	@GetMapping("/addReader")
	public String showCreateForm(Reader reader) {
		return "add-reader";
	}

	@PostMapping("/add-reader")
	public String createReader(@Valid @ModelAttribute("reader") Reader reader, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-reader";
		}

		readerService.createReader(reader);
		model.addAttribute("reader", readerService.findAllReaders());
		return "redirect:/readers";
	}

	@GetMapping("/updateReader/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		model.addAttribute("reader", readerService.findReaderById(id));
		return "update-reader";
	}

	@PostMapping("/update-reader/{id}")
	public String updateUser(@Valid @ModelAttribute("reader") @PathVariable("id") Long id, Reader reader, BindingResult result, Model model) {
		if (result.hasErrors()) {
			reader.setId(id);
			return "update-reader";
		}

		readerService.updateReader(reader);
		model.addAttribute("reader", readerService.findAllReaders());
		return "redirect:/readers";
	}

	@RequestMapping("/removeReader/{id}")
	public String deleteReader(@PathVariable("id") Long id, Model model) {
		readerService.deleteReader(id);

		model.addAttribute("reader", readerService.findAllReaders());
		return "redirect:/readers";
	}

}
