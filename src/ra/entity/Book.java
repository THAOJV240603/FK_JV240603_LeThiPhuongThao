package ra.entity;

import java.util.Scanner;

public class Book implements IBookManagement{
    private int BookId;
    private String BookName;
    private String Title;
    private String Author;
    private int TotalPages;
    private String Content;
    private String Publisher;
    private double Price;
    private int TypeId;
    private boolean IsDeleted;

    //Constructor
    public Book() {
    }

    public Book(int bookId, String bookName, String title, String author, int totalPages, String content, String publisher, double price, int typeId, boolean isDeleted) {
        BookId = bookId;
        BookName = bookName;
        Title = title;
        Author = author;
        TotalPages = totalPages;
        Content = content;
        Publisher = publisher;
        Price = price;
        TypeId = typeId;
        IsDeleted = isDeleted;
    }

    //Getter, setter
    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(int totalPages) {
        TotalPages = totalPages;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int typeId) {
        TypeId = typeId;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Tên sách:");
        this.BookName = scanner.nextLine();
        System.out.println("Tiêu đề sách:");
        this.Title = scanner.nextLine();
        System.out.println("Tên tác giả:");
        this.Author = scanner.nextLine();
        System.out.println("Số trang sách:");
        this.TotalPages = Integer.parseInt(scanner.nextLine());
        System.out.println("Nội dung sách:");
        this.Content = scanner.nextLine();
        System.out.println("Nhà xuất bản:");
        this.Publisher = scanner.nextLine();
        System.out.println("Giá sách:");
        this.Price = Double.parseDouble(scanner.nextLine());
        System.out.println("Mã loại sách:");
        this.TypeId = Integer.parseInt(scanner.nextLine());
        System.out.println("Trạng thái:");
        this.IsDeleted = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void displayData() {
        //System.out.println("----- Thông tin sách -----");
        System.out.println("Mã sách: " + this.BookId);
        System.out.println("Tên sách: " + this.BookName);
        System.out.println("Tiêu đề sách: " + this.Title);
        System.out.println("Tên tác giả: " + this.Author);
        System.out.println("Số trang sách: " + this.TotalPages);
        System.out.println("Nội dung sách: " + this.Content);
        System.out.println("Nhà xuất bản: " + this.Publisher);
        System.out.println("Giá sách: " + this.Price);
        System.out.println("Mã loại sách: " + this.TypeId);
        System.out.println("Trạng thái: " + (this.IsDeleted ? "Chưa xóa" : "Đã xóa"));
        System.out.println("\n");
    }
}
