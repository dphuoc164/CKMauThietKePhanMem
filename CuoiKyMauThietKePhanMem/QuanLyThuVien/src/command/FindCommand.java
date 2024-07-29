package command;

import domain.LibraryService;

public class FindCommand extends Command {
    private int bookId;

    public FindCommand(LibraryService libraryService, int bookId) {
        super(libraryService);
        this.bookId = bookId;
    }

    @Override
    public void execute() {
        libraryService.findBook(bookId);
    }

}
