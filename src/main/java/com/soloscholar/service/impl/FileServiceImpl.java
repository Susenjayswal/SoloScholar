package com.soloscholar.service.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.soloscholar.constant.Item;
import com.soloscholar.service.AuthorService;
import com.soloscholar.service.BookService;
import com.soloscholar.service.CategoryService;
import com.soloscholar.service.FileService;
import com.soloscholar.service.PublisherService;
import com.soloscholar.util.Mapper;
import com.soloscholar.vo.AuthorRecord;
import com.soloscholar.vo.BookRecord;
import com.soloscholar.vo.CategoryRecord;
import com.soloscholar.vo.PublisherRecord;


@Service
public class FileServiceImpl implements FileService {

	private final BookService bookService;

	private final AuthorService authorService;

	private final PublisherService publisherService;

	private final CategoryService categoryService;

	public FileServiceImpl(BookService bookService, AuthorService authorService, PublisherService publisherService,
			CategoryService categoryService) {
		this.authorService = authorService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
		this.bookService = bookService;
	}

	@Override
	public void exportCSV(String fileName, HttpServletResponse response)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		var item = Item.getItemByValue(fileName);
		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + item.get().getFileName() + "\"");

		switch (item.get()) {
		case BOOK:
			StatefulBeanToCsv<BookRecord> writer1 = getWriter(response.getWriter());
			writer1.write(Mapper.bookModelToVo(bookService.findAllBooks()));
			break;
		case AUTHOR:
			StatefulBeanToCsv<AuthorRecord> writer2 = getWriter(response.getWriter());
			writer2.write(Mapper.authorModelToVo(authorService.findAllAuthors()));
			break;
		case CATEGORY:
			StatefulBeanToCsv<CategoryRecord> writer3 = getWriter(response.getWriter());
			writer3.write(Mapper.categoryModelToVo(categoryService.findAllCategories()));
			break;
		case PUBLISHER:
			StatefulBeanToCsv<PublisherRecord> writer4 = getWriter(response.getWriter());
			writer4.write(Mapper.publisherModelToVo(publisherService.findAllPublishers()));
			break;
		}

	}

	private static <T> StatefulBeanToCsv<T> getWriter(PrintWriter printWriter) {
		return new StatefulBeanToCsvBuilder<T>(printWriter).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
				.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false).build();
	}
}
