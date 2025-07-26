package entity;

import dao.BookDao;

import java.time.Year;
import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private Year published_year;
    private double price;

    public Book() {
    }

    public Book(int id, String title, String author, Year published_year, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.published_year = published_year;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublished_year() {
        return published_year.getValue();
    }

    public void setPublished_year(Year published_year) {
        this.published_year = published_year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Thông tin sách{" +
                "Mã sách: " + id +
                " | Tên: " + title + '\'' +
                " | Tác giả: " + author + '\'' +
                " | Năm xuất bản: " + published_year +
                " | Giá: " + price +
                '}';
    }
    public void inputData(Scanner scanner) {
       checkExistBookNameAndAuthor(scanner);
       this.published_year = Validator.getYear(scanner, "Mời nhập vào năm xuất bản:");
       this.price = Validator.getDouble(scanner,"Nhập vào giá sách:");
    }
    public  void checkExistBookNameAndAuthor(Scanner scanner) {
        String title;
        String author;
        do {
          title = Validator.getString(scanner, "Mời nhập vào tên sách:");
          author = Validator.getString(scanner, "Mời nhập vào tên tác giả:");
          int countBook = BookDao.check_exist_book_by_title_and_author(title, author);
          if (countBook > 0) {
              System.out.println("Sách đã tồn tại. Vui lòng nhập lại");
          }else {
             this.title = title;
             this.author = author;
            break;
          }
        }while(true);
    }
}
