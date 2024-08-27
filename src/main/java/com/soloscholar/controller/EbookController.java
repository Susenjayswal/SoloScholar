package com.soloscholar.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.soloscholar.entity.Book;
import com.soloscholar.entity.Ebook;
import com.soloscholar.service.EbookService;

@Controller
public class EbookController {

    @Autowired
    private EbookService ebookService;

    @GetMapping("/list")
    public String viewHomePage(Model model) {
        model.addAttribute("listEbooks", ebookService.getAllEbooks());
        return "list-ebooks";
    }

    @GetMapping("/ebooklist")
    public String viewEbookPage(Model model) {
        model.addAttribute("listEbooks", ebookService.getAllEbooks());
        return "list-ebook";
    }

    @GetMapping("/showNewBookForm")
    public String showNewBookForm(Model model) {
        Ebook book = new Ebook();
        model.addAttribute("book", book);
        return "add-ebook";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("ebook") Ebook ebook, @RequestParam("file") MultipartFile file) {
        try {
            ebook.setPdfFile(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ebookService.saveBook(ebook);
        return "redirect:/list";
    }

    @GetMapping("/updateebook/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		model.addAttribute("ebook", ebookService.getEbookById(id));
		return "update-ebook";
	}
    @RequestMapping("/update-ebook/{id}")
	public String updateEbook(@Valid @ModelAttribute("ebook") @PathVariable("id") Long id, Ebook ebook, BindingResult result, Model model) {
		if (result.hasErrors()) {
			ebook.setId(id);
			return "update-ebook";
		}

		ebookService.updateEbook(ebook);
		model.addAttribute("ebook", ebookService.getAllEbooks());
		return "redirect:/list";
	}
    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable(value = "id") long id) {
        ebookService.deleteEbookById(id);
        return "redirect:/list";
    }

    @GetMapping("/viewBook/{id}")
    public ResponseEntity<byte[]> viewBook(@PathVariable("id") Long id) {
        Ebook ebook = ebookService.getEbookById(id);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + ebook.getBookName() + ".pdf\"")
            .contentType(MediaType.APPLICATION_PDF)
            .body(ebook.getPdfFile());
    }
}

