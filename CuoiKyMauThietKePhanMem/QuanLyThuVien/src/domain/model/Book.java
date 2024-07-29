package domain.model;

import java.util.Date;

public abstract class Book {
    private int id;
    private double price;
    private int quantity;
    private String publisher;
    private Date createdAt;

    public Book() {
    }

    public Book(int id, double price, int quantity, String publisher, Date createAt) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.publisher = publisher;
        this.createdAt = createAt;
    }

    public abstract double totalPrice();

    public int getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
