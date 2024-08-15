package com.soloscholar.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.soloscholar.entity.Expenditure;
import com.soloscholar.service.ExpenditureService;


@Controller
public class ExpenditureController {

	private final ExpenditureService expenditureService;

	public ExpenditureController(ExpenditureService expenditureService) {
		this.expenditureService = expenditureService;
	}

	@GetMapping("/expenditures")
	public String findAllExpenditure(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		var currentPage = page.orElse(1);
		var pageSize = size.orElse(5);
		var bookPage = expenditureService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("expenditures", bookPage);

		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "list-expenditures";
	}

	@PostMapping("/expenditures/{id}")
	public String findExpednitureById(@PathVariable("id") Long id, Model model) {

		model.addAttribute("expenditures",  expenditureService.findExpenditureById(id));
		return "list-expenditure";
	}

	@GetMapping("/addExpenditures")
	public String showCreateForm(Expenditure expenditure) {
		return "add-expenditure";
	}

	@PostMapping("/add-expenditure")
	public String createExpenditure(Expenditure expenditure, BindingResult result, Model model) {
		

		try{expenditureService.createExpenditure(expenditure);
		model.addAttribute("expenditure", expenditureService.findAllExpenditure());
		}catch(Exception e) {System.out.println("Error "+ e);}
		return "redirect:/expenditures";
	}

	@GetMapping("/updateExpenditure/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		model.addAttribute("expenditures", expenditureService.findExpenditureById(id));
		return "update-expenditure";
	}

	@RequestMapping("/update-expenditure/{id}")
	public String updateAuthor(@PathVariable("id") Long id, Expenditure expenditure, BindingResult result, Model model) {
		if (result.hasErrors()) {
			expenditure.setId(id);
			return "update-expenditure";
		}

		expenditureService.updateExpenditure(expenditure);
		model.addAttribute("expenditures", expenditureService.findAllExpenditure());
		return "redirect:/expenditures";
	}

	@RequestMapping("/remove-expenditure/{id}")
	public String deleteExpenditure(@PathVariable("id") Long id, Model model) {
		expenditureService.deleteExpenditure(id);

		model.addAttribute("expenditure", expenditureService.findAllExpenditure());
		return "redirect:/expenditures";
	}

}
