package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.model.Book;
import domain.model.ReferenceBook;
import domain.model.TextBook;

public class LibraryPersistence {
    private Connection connection = null;

    public LibraryPersistence() throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/dblybrary";
        String user = "root";
        String password = "123456789";
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection.toString());
            System.out.println("Connect Success");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void addBook(Book book) {

        String query = "INSERT INTO Books (Entry_Date, Price, Quantity, Publisher, `Condition`, Tax) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, new java.sql.Date(book.getCreatedAt().getTime()));
            preparedStatement.setDouble(2, book.getPrice());
            preparedStatement.setDouble(3, book.getQuantity());
            preparedStatement.setString(4, book.getPublisher());

            if (book instanceof TextBook) {
                preparedStatement.setString(5, ((TextBook) book).getCondition());
                preparedStatement.setNull(6, Types.FLOAT);
            } else if (book instanceof ReferenceBook) {
                preparedStatement.setNull(5, Types.VARCHAR);
                preparedStatement.setDouble(6, ((ReferenceBook) book).getTax());
            }

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm thành công");

        } catch (SQLException e) {
            String errorMessage = "Lỗi khi thêm sách: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage);
            e.printStackTrace();
        } catch (Exception e) {
            String errorMessage = "Đã xảy ra lỗi: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage);
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String publisher = resultSet.getString("Publisher");
                String condition = resultSet.getString("Condition");
                Date createAt = resultSet.getDate("Entry_Date");
                Integer id = resultSet.getInt("Book_Id");
                Double price = resultSet.getDouble("Price");
                int quantity = resultSet.getInt("Quantity");
                Double tax = resultSet.getDouble("Tax");
                Book book;
                if (tax == null || tax <= 0) {
                    book = new TextBook(id, price, quantity, publisher, createAt, condition);
                } else if (condition == null) {
                    book = new ReferenceBook(id, price, quantity, publisher, createAt, tax);
                } else {
                    continue;
                }

                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book findBookById(int id) {
        String sql = "SELECT * FROM books where Book_Id =" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String publisher = resultSet.getString("Publisher");
                String condition = resultSet.getString("Condition");
                Date createAt = resultSet.getDate("Entry_Date");
                Integer bookId = resultSet.getInt("Book_Id");
                Double price = resultSet.getDouble("Price");
                int quantity = resultSet.getInt("Quantity");
                Double tax = resultSet.getDouble("Tax");
                if (tax == null || tax <= 0) {
                    Book book = new TextBook(bookId, price, quantity, publisher, createAt, condition);
                    return book;
                } else if (condition == null) {
                    Book book = new ReferenceBook(bookId, price, quantity, publisher, createAt, tax);
                    return book;

                } else {
                    continue;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBook(Book book) {

        String query = "UPDATE Books SET Entry_Date = ?, Price = ?, Quantity = ?, Publisher = ?, `Condition` = ?, Tax = ? WHERE Book_Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, new java.sql.Date(book.getCreatedAt().getTime()));
            preparedStatement.setDouble(2, book.getPrice());
            preparedStatement.setDouble(3, book.getQuantity());
            preparedStatement.setString(4, book.getPublisher());
            preparedStatement.setInt(7, book.getId());
            if (book instanceof TextBook) {
                preparedStatement.setString(5, ((TextBook) book).getCondition());
                preparedStatement.setNull(6, Types.FLOAT);
            } else if (book instanceof ReferenceBook) {
                preparedStatement.setNull(5, Types.VARCHAR);
                preparedStatement.setDouble(6, ((ReferenceBook) book).getTax());
            }

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
        } catch (SQLException e) {
            String errorMessage = "Lỗi khi cập nhật sách: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage);
            e.printStackTrace();
        } catch (Exception e) {
            String errorMessage = "Đã xảy ra lỗi: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage);
            e.printStackTrace();
        }
    }

    public void removeBookById(int id) {

        String query = "DELETE FROM Books  WHERE Book_Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        } catch (SQLException e) {
            String errorMessage = "Lỗi khi xóa sách: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage);
            e.printStackTrace();
        } catch (Exception e) {
            String errorMessage = "Đã xảy ra lỗi: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage);
            e.printStackTrace();
        }
    }

    public List<Book> getBooksByPublisher(String _publisher) {
        System.out.println(_publisher);
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE Publisher = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, _publisher);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String publisher = resultSet.getString("Publisher");
                String condition = resultSet.getString("Condition");
                Date createAt = resultSet.getDate("Entry_Date");
                Integer id = resultSet.getInt("Book_Id");
                Double price = resultSet.getDouble("Price");
                int quantity = resultSet.getInt("Quantity");
                Double tax = resultSet.getDouble("Tax");
                Book book;
                if (tax == null || tax <= 0) {
                    book = new TextBook(id, price, quantity, publisher, createAt, condition);
                } else if (condition == null) {
                    book = new ReferenceBook(id, price, quantity, publisher, createAt, tax);
                } else {
                    continue;
                }

                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public double agvPriceReferenceBook() {
        String sql = "SELECT AVG(price) as avg FROM books WHERE tax >= 0 AND `condition` IS NULL";
        double total = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Double price = resultSet.getDouble("avg");
                total += price;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}
