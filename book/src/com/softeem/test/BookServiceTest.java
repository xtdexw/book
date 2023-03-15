package com.softeem.test;

import com.softeem.bean.Tbook;
import com.softeem.service.impl.BookServiceImpl;
import org.junit.Test;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.sql.SQLException;

public class BookServiceTest {
    private BookServiceImpl bookService = new BookServiceImpl();

    @Test
    public void addBook() throws SQLException {
        bookService.addBook(new Tbook(null, "小明在手，天下我有！", "1125", new BigDecimal(1000000), 100000000, 0, null));
    }


    @Test
    public void deleteBookById() throws SQLException {
        bookService.deletebookById(19);
    }

    @Test
    public void updateBook() throws SQLException {
        bookService.updateBook(new Tbook(19,"社会我小明，人狠话不多！", "1125", new BigDecimal(999999), 10, 111110, null));
    }

    @Test
    public void queryBookById() throws SQLException {
        System.out.println(bookService.queryBookById(19));
    }

    @Test
    public void queryBooks() throws SQLException {
        for (Tbook queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

}
