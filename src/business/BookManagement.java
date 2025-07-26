package business;

import dao.BookDao;
import entity.Book;
import entity.Validator;
import utils.ConnectionDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static void displayBookList() {
        List<Book> bookList = BookDao.getBookList();
        bookList.forEach(System.out::println);
    }

    public static void addBook(Scanner scanner) {
        Book book = new Book();
        book.inputData(scanner);
        boolean result = BookDao.add_book(book);
        if (result) {
            System.out.println("Thêm sách thành công");
        } else {
            System.err.println("Thêm sách thất bại");
        }
    }

    public static void updateBook(Scanner scanner) {
        int id = Validator.getInt(scanner, "Mời nhập vào mã sách muốn cập nhật");
        Book book = BookDao.check_book_by_id(id);
        if (book == null) {
            System.out.println("Mã sách không tồn tại");
        } else {
//            book.inputData(scanner);
            boolean exit = false;
            do {
                System.out.println("******Cập　nhật thông tin sách");
                System.out.println("1. Cập nhật tên sách và tên tác giả");
                System.out.println("2. Cập nhật năm xuất bản");
                System.out.println("3. Cập nhật giá");
                System.out.println("4. Thoát");
                int choice = Validator.getInt(scanner, "Mời nhập vào lựa chọn của bạn:");

                switch (choice) {
                    case 1:
                        book.checkExistBookNameAndAuthor(scanner);
                        break;
                    case 2:
                        Validator.getYear(scanner, "Mời nhập vào năm xuất bản mới của sách");
                        break;
                    case 3:
                        Validator.getDouble(scanner, "Mời nhập vào giá mới của sách");
                        break;
                    case 4:
                        exit = true;
                        break;
                        default:
                            System.err.println("Vui lòng nhập từ 1-4");
                }

            } while (exit);
            Boolean result = BookDao.update_book(book);
            if (result) {
                System.out.println("Cập nhật thành công");
            }else {
                System.err.println("Cập nhật thất bại");
            }
        }
    }
    public static void deleteBook(Scanner scanner) {
        int id = Validator.getInt(scanner, "Mời nhập vào mã sách muốn xóa:");
        Book book = BookDao.check_book_by_id(id);
        if (book == null) {
            System.out.println("Mã sách không tồn tại");
        }else {
            Boolean result = BookDao.delete_book(id);
            if (result) {
                System.out.println("Xóa thành công");
            }else {
                System.out.println("Xóa thất bại");
            }
        }
    }
    public static void findBookByName(Scanner scanner) {
        String titleSearch = Validator.getString(scanner, "Nhập vào tên sách muốn tìm:");
        List<Book> listBook = BookDao.find_book_by_name(titleSearch);
        if (listBook == null) {
            System.out.println("Không tìm thấy sách có tên là "+titleSearch);
        }else {
            listBook.forEach(System.out::println);
        }
    }
}
