package domain.model;

import java.util.Date;

public class ReferenceBook extends Book {
    private double tax;

    public ReferenceBook() {
        super();
    }

    public ReferenceBook(int id, double price, int quantity, String publisher, Date createAt,
            double tax) {
        super(id, price, quantity, publisher, createAt);
        this.tax = tax;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    @Override
    public double totalPrice() {
        return getPrice() * getQuantity() + getTax();
    }
}
