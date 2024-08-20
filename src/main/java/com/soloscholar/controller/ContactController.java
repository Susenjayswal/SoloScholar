package com.soloscholar.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.soloscholar.entity.Contact;
import com.soloscholar.service.ContactService;





@Controller
public class ContactController {

	private final ContactService contactService;

	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	@GetMapping("/contacts")
	public String findAllExpenditure(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		var currentPage = page.orElse(1);
		var pageSize = size.orElse(5);
		var bookPage = contactService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("contacts", bookPage);

		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "list-contacts";
	}

	@PostMapping("/contacts/{id}")
	public String findContactById(@PathVariable("id") Long id, Model model) {

		model.addAttribute("contacts",  contactService.findContactById(id));
		return "list-contact";
	}

	@GetMapping("/addContact")
	public String showCreateForm(Contact contact) {
		return "add-contact";
	}

	@PostMapping("/add-contact")
	public String createContact(@Valid @ModelAttribute("contacts") Contact contact, BindingResult result, Model model) {
		

		contactService.createContact(contact);
		model.addAttribute("contacts", contactService.findAllContact());
		
		return "redirect:/";
	}

	@GetMapping("/updateContacts/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		model.addAttribute("contacts", contactService.findContactById(id));
		return "update-contact";
	}

	@PutMapping("/update-contact/{id}")
	public String updateAuthor(@PathVariable("id") Long id, Contact contact, BindingResult result, Model model) {
		if (result.hasErrors()) {
			contact.setId(id);
			return "update-contact";
		}

		contactService.updateContact(contact);
		model.addAttribute("contacts", contactService.findAllContact());
		return "redirect:/contacts";
	}

	@RequestMapping("/remove-contact/{id}")
	public String deleteExpenditure(@Valid @ModelAttribute("contact") @PathVariable("id") Long id, Model model) {
		contactService.deleteContact(id);

		model.addAttribute("contact", contactService.findAllContact());
		return "redirect:/contacts";
	}

}
