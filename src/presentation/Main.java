package presentation;

import business.BookManagement;
import entity.Validator;

import java.sql.SQLOutput;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******QUẢN LÝ SÁCH*****");
            System.out.println("1. Hiển thị danh sách sách.");
            System.out.println("2. Thêm sách.");
            System.out.println("3. Cập nhật thông tin sách.");
            System.out.println("4. Xóa sách.");
            System.out.println("5. Tìm sách theo tên.");
            System.out.println("6. Thoát");
            int choice = Validator.getInt(scanner, "Mời nhập vào lựa chọn của bạn:");
            switch (choice) {
                case 1:
                    BookManagement.displayBookList();
                    break;
                case 2:
                    BookManagement.addBook(scanner);
                    break;
                case 3:
                    BookManagement.updateBook(scanner);
                    break;
                case 4:
                    BookManagement.deleteBook(scanner);
                    break;
                case 5:
                    BookManagement.findBookByName(scanner);
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-6");
            }
        } while (true);
    }
}