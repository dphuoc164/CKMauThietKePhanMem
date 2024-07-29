package command;

import domain.LibraryService;

public abstract class Command {
    protected LibraryService libraryService;

    public Command(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public abstract void execute();
}