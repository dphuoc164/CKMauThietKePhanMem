import domain.LibraryServiceImpl;
import persistence.LibraryPersistence;
import presentation.LibraryView;

public class App {
    public static void main(String[] args) throws Exception {
        LibraryPersistence persistence = new LibraryPersistence();
        LibraryServiceImpl domain = new LibraryServiceImpl(persistence);
        LibraryView ui = new LibraryView(domain);
        domain.subcribe(ui);
    }
}
