package domain;

import java.util.List;

import domain.model.Book;

public interface LibraryService {
    void addBook(Book newBook);

    void updateBook(Book book);

    List<Book> getAllBooks();

    List<Book> getBooksByPublisher(String publisher);

    double getTotalPriceTextBook();

    double getTotalPriceRefeneBook();

    double getAvgPriceRefeneBook();

    Book findBook(int id);

    void removeBook(int id);
}
