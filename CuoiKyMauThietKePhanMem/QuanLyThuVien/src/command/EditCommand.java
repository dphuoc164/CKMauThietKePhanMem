package command;

import domain.LibraryService;
import domain.model.Book;

public class EditCommand extends Command {

    private Book book;

    public EditCommand(LibraryService libraryService, Book book) {
        super(libraryService);
        this.book = book;
    }

    @Override
    public void execute() {
        libraryService.updateBook(book);
    }

}
