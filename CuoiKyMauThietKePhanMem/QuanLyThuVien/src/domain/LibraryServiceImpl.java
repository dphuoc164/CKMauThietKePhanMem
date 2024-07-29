package domain;

import java.util.List;

import domain.model.Book;
import domain.model.ReferenceBook;
import domain.model.TextBook;
import observer.Publisher;
import persistence.LibraryPersistence;

public class LibraryServiceImpl extends Publisher implements LibraryService {
    private LibraryPersistence persistence;

    public LibraryServiceImpl(LibraryPersistence db) {
        this.persistence = db;
    }

    @Override
    public void addBook(Book book) {
        persistence.addBook(book);
        notifyChange();
    }

    @Override
    public List<Book> getAllBooks() {
        return persistence.getAllBooks();
    }

    @Override
    public double getTotalPriceTextBook() {
        List<Book> books = persistence.getAllBooks();
        double total = 0;
        for (Book book : books) {
            if (book instanceof ReferenceBook) {
                continue;
            } else {
                TextBook textBook = (TextBook) book;

                total += textBook.totalPrice();
            }
        }

        return total;
    }

    @Override
    public double getTotalPriceRefeneBook() {
        List<Book> books = persistence.getAllBooks();
        double total = 0;
        for (Book book : books) {
            if (book instanceof TextBook) {
                continue;
            } else {
                ReferenceBook referenceBook = (ReferenceBook) book;
                total += referenceBook.totalPrice();

            }
        }

        return total;
    }

    @Override
    public Book findBook(int id) {
        return persistence.findBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        persistence.updateBook(book);
        notifyChange();
    }

    @Override
    public void removeBook(int id) {
        persistence.removeBookById(id);
        notifyChange();
    }

    @Override
    public List<Book> getBooksByPublisher(String publisher) {
        return persistence.getBooksByPublisher(publisher);
    }

    @Override
    public double getAvgPriceRefeneBook() {
        return persistence.agvPriceReferenceBook();
    }
}
