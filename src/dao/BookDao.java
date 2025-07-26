package dao;

import entity.Book;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public static List<Book> getBookList() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Book> bookList = null;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call find_book_all()}");
            ResultSet rs = callSt.executeQuery();
            bookList = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublished_year(Year.of(rs.getInt("published_year")));
                book.setPrice(rs.getDouble("price"));
                bookList.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return bookList;
    }

    public static int check_exist_book_by_title_and_author(String title, String author) {
        Connection conn = null;
        CallableStatement callSt = null;
        int countBook = 0;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call check_book_exist_by_name_and_author(?,?,?)}");
            callSt.setString(1, title);
            callSt.setString(2, author);
            callSt.execute();
            countBook = callSt.getInt(3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return countBook;
    }

    public static boolean add_book(Book book) {
        Connection conn = null;
        CallableStatement callSt = null;
        Boolean result = false;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call add_book(?,?,?,?)}");
            callSt.setString(1, book.getTitle());
            callSt.setString(2, book.getAuthor());
            callSt.setInt(3, book.getPublished_year());
            callSt.setDouble(4, book.getPrice());
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static Book check_book_by_id(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Book book = null;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call find_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublished_year(Year.of(rs.getInt("published_year")));
                book.setPrice(rs.getDouble("price"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return book;
    }

    public static boolean update_book(Book book) {
        Connection conn = null;
        CallableStatement callSt = null;
        Boolean result = false;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call update_book(?,?,?,?,?)}");
            callSt.setInt(1, book.getId());
            callSt.setString(2, book.getTitle());
            callSt.setString(3, book.getAuthor());
            callSt.setInt(4, book.getPublished_year());
            callSt.setDouble(5, book.getPrice());
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
    public static boolean delete_book(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call delete_book(?)}");
            callSt.setInt(1, id);
           callSt.executeUpdate();
           result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
    public static List<Book> find_book_by_name(String title) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Book> bookList = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call find_book_by_name(?)}");
            callSt.setString(1, title);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublished_year(Year.of(rs.getInt("published_year")));
                book.setPrice(rs.getDouble("price"));
                bookList.add(book);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return bookList;
    }
}
