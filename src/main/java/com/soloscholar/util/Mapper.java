package com.soloscholar.util;

import java.util.List;
import java.util.stream.Collectors;

import com.soloscholar.entity.Author;
import com.soloscholar.entity.Book;
import com.soloscholar.entity.Category;
import com.soloscholar.entity.Contact;
import com.soloscholar.entity.Expenditure;
import com.soloscholar.entity.Publisher;
import com.soloscholar.entity.User;
import com.soloscholar.vo.AuthorRecord;
import com.soloscholar.vo.BookRecord;
import com.soloscholar.vo.CategoryRecord;
import com.soloscholar.vo.ContactRecord;
import com.soloscholar.vo.ExpenditureRecord;
import com.soloscholar.vo.PublisherRecord;
import com.soloscholar.vo.UserRecord;

public class Mapper {

	public static List<BookRecord> bookModelToVo(List<Book> books) {

		return books.stream().map(vo -> {
			var bookVo = new BookRecord(vo.getId(), vo.getIsbn(), vo.getName(), vo.getSerialName(),
					vo.getDescription());
			return bookVo;
		}).collect(Collectors.toList());
	}

	public static List<AuthorRecord> authorModelToVo(List<Author> authors) {

		return authors.stream().map(vo -> {
			var authorVo = new AuthorRecord(vo.getId(), vo.getName(), vo.getDescription());
			return authorVo;
		}).collect(Collectors.toList());

	}

	public static List<CategoryRecord> categoryModelToVo(List<Category> categories) {

		return categories.stream().map(vo -> {
			var categoryVo = new CategoryRecord(vo.getId(), vo.getName());
			return categoryVo;
		}).collect(Collectors.toList());

	}

	public static List<PublisherRecord> publisherModelToVo(List<Publisher> publishers) {

		return publishers.stream().map(vo -> {
			var publisherVo = new PublisherRecord(vo.getId(), vo.getName());
			return publisherVo;
		}).collect(Collectors.toList());

	}
	public static List<ExpenditureRecord> expenditureModelToVo(List<Expenditure> expenditure) {

		return expenditure.stream().map(vo -> {
			var expenditureVo = new ExpenditureRecord(
					vo.getId(),
					vo.getInvoiceNumber(),
					vo.getCategory(),
					vo.getType(),
					vo.getRate(),
					vo.getQuantity(),
					vo.getDate(),
					vo.getTotalPrice()
					);
					
			return expenditureVo;
		}).collect(Collectors.toList());

	}
	public static List<ContactRecord> contactModelToVo(List<Contact> contact) {

		return contact.stream().map(vo -> {
			var contactVo = new ContactRecord(
					vo.getId(),
					vo.getName(),
					vo.getEmail(),
					vo.getMessage(),
					vo.getDate(),
					vo.getPhone()
					);
					
			return contactVo;
		}).collect(Collectors.toList());

	}

	
	public static List<UserRecord> userModelToVo(List<User> users) {
	    return users.stream().map(vo -> {
	        var uservo= new UserRecord(
	            vo.getId(), 
	            vo.getFirstName(), 
	            vo.getLastName(), 
	            vo.getAddress(), 
	            vo.getEmail(), 
	            vo.getAge(),
	            vo.getPhone(),
	            vo.getGender(),
	            vo.getIdtype(),
	            vo.getIdnum(),
	            vo.getRoles(),
	            vo.getPassword()
	        );
	        return uservo;
	        		}).collect(Collectors.toList());
	}

	}


