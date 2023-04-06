package org.example.dto;

import org.example.model.Person;

import java.util.Date;

public class BookDto {

    private String bookName;
    private String author;
    private int yearOfPublication;
    private Date tookAt;
    private Person owner;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Date getTookAt() {
        return tookAt;
    }

    public void setTookAt(Date tookAt) {
        this.tookAt = tookAt;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
