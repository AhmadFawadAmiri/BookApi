package com.example.bookapi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class BookDTO {
    private long id;
    @NotBlank(message = "Title must not be blank")
    private String title;
    @Positive(message = "Price must be positive")
    private double price;

    private long authorId;

    public BookDTO() {}

    public BookDTO(long id, String title, double price, long authorId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.authorId = authorId;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getAuthorId() {
        return authorId;
    }
    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}

