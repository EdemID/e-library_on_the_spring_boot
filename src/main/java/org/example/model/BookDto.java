package org.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import org.example.entity.Person;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {

    @NotEmpty(message = "Name should not be empty")
    private String bookName;

    @NotEmpty(message = "Author should not be empty")
    private String author;

    private int yearOfPublication;

    @Schema(hidden = true)
    private Date takenAt;

    @Schema(hidden = true)
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

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
