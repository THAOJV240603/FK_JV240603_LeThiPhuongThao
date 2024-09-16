package ra.presentation;

import ra.business.BookBusiness;
import ra.business.BookTypeBusiness;
import ra.entity.Book;
import ra.entity.BookType;

import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("******************BOOK-MANAGEMENT******************\n" +
                    "1. Quản lý loại sách\n" +
                    "2. Quản lý sách\n" +
                    "3. Thoát \n");
            System.out.println("Lựa chọn của bạn:");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    boolean check = true;
                    do{
                        System.out.println("**********************BOOKTYPE-MENU********************\n" +
                                "1. Danh sách loại sách\n" +
                                "2. Tạo mới loại sách\n" +
                                "3. Cập nhật thông tin loại sách\n" +
                                "4. Xóa loại sách\n" +
                                "5. Thống kê số lượng sách theo mã loại sách\n" +
                                "6. Thoát (Trở về Menu chính)\n");
                        System.out.println("Lựa chọn của bạn:");
                        int choice1 = Integer.parseInt(sc.nextLine());
                        switch (choice1) {
                            case 1:
                                BookManagement.findById(sc);
                                break;
                            case 2:
                                BookManagement.createBookType(sc);
                                break;
                            case 3:
                                BookManagement.updateBookType(sc);
                                break;
                            case 4:
                                BookManagement.deleteBookType(sc);
                                break;
                            case 5:
                                break;
                            case 6:
                                check = false;
                            default:
                                System.out.println("Sai lựa chọn");
                        }
                    }while(check);
                    break;
                case 2:
                    boolean check1 = true;
                    do{
                        System.out.println("***********************BOOK-MENU***********************\n" +
                                "1. Danh sách sách\n" +
                                "2. Tạo mới sách\n" +
                                "3. Cập nhật thông tin sách\n" +
                                "4. Xóa sách\n" +
                                "5. Hiển thị danh sách các cuốn sách theo giá giảm dần\n" +
                                "6. Tìm kiếm sách theo tên hoặc nội dung\n" +
                                "7. Thống kê số lượng sách theo nhóm \n" +
                                "8. Thoát (Trở về Menu chính)\n");
                        System.out.println("Lựa chọn của bạn:");
                        int choice2 = Integer.parseInt(sc.nextLine());
                        switch (choice2) {
                            case 1:
                                BookManagement.findByIdBook(sc);
                                break;
                            case 2:
                                BookManagement.createBook(sc);
                                break;
                            case 3:
                                BookManagement.updateBook(sc);
                                break;
                            case 4:
                                BookManagement.deleteBook(sc);
                                break;
                            case 5:

                                break;
                            case 6:
                                BookManagement.searchBook(sc);
                                break;
                            case 7:
                                break;
                            case 8:
                                check1 = false;
                            default:
                                System.out.println("Sai lựa chọn");
                        }
                    }while(check1);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Sai lựa chọn");
            }
        }while(true);


    }
    ////Loại sách
    //Ds
    public static void findById(Scanner sc){
        List<BookType> bookTypes = BookTypeBusiness.getBookTypes();
        for (BookType bookType : bookTypes) {
            bookType.displayData();
        }
    }

    //Tạo mới
    public static void createBookType(Scanner sc){
        BookType bookType = new BookType();
        bookType.inputData(sc);
        BookTypeBusiness.create(bookType);
    }

    //Update
    public static  void updateBookType(Scanner sc){
        System.out.println("Nhập vào mã loại sách cần sửa:");
        int idEdit = Integer.parseInt(sc.nextLine());
        BookType bookType = BookTypeBusiness.findById(idEdit);
        if(bookType!=null){
            System.out.println("Thông tin loại sách:");
            bookType.displayData();

            boolean isExist = true;
            do{
                System.out.println("Chọn trường muốn nhập:");
                System.out.println("1. Sửa tên loại sách:");
                System.out.println("2. Sửa mô tả:");
                System.out.println("3. Sửa trạng thái:");
                System.out.println("4. Thoát");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập tên mới:");
                        bookType.setTypeName(sc.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập mô tả:");
                        bookType.setDescription(sc.nextLine());
                        break;
                    case 3:
                        System.out.println("Nhập trạng thái:");
                        bookType.setDeleted(Boolean.parseBoolean(sc.nextLine()));
                        break;
                    case 4:
                        isExist = false;
                        break;
                    default:
                        System.out.println("Sai lựa chọn");
                }
            }while(isExist);

            boolean result = BookTypeBusiness.update(bookType);
            if(result){
                System.out.println("Cập nhật thành công");
                }else {
                System.out.println("Cập nhật thất bại");
                }
            }else {
                System.out.println("Không tìm thấy loại sách");
            }
        }
    //Xóa
    public static void deleteBookType(Scanner sc){
        System.out.println("Nhập vào mã loại sách muốn xóa:");
        int idDelete = Integer.parseInt(sc.nextLine());
        if(BookTypeBusiness.findById(idDelete) != null){
            boolean result = BookTypeBusiness.delete(idDelete);
            if(result){
                System.out.println("Xóa thành công");
            }else{
                System.out.println("Xóa thất bại");
            }
        }else {
            System.out.println("Mã loại sách không tồn tại");
        }
    }

    ////Sách
    //Ds
    public static void findByIdBook(Scanner sc){
        List<Book> books = BookBusiness.getBooks();
        for (Book book : books) {
            book.displayData();
        }
    }

    //Tạo mới
    public static void createBook(Scanner sc){
        Book book = new Book();
        book.inputData(sc);
        BookBusiness.create(book);
    }

    //Update
    public static  void updateBook(Scanner sc){
        System.out.println("Nhập vào mã sách cần sửa:");
        int idEdit = Integer.parseInt(sc.nextLine());
        Book book = BookBusiness.findById(idEdit);
        if(book!=null){
            System.out.println("Thông tin sách:");
            book.displayData();

            boolean isExist = true;
            do{
                System.out.println("Chọn trường muốn nhập:");
                System.out.println("1. Sửa tên sách");
                System.out.println("2. Sửa tiêu đề");
                System.out.println("3. Sửa tên tác giả");
                System.out.println("4. Sửa số trang sách");
                System.out.println("5. Sửa nội dung sách");
                System.out.println("6. Sửa tên nhà xuất bản");
                System.out.println("7. Sửa giá");
                System.out.println("8. Sửa mã loại sách");
                System.out.println("9. Sửa trạng thái");
                System.out.println("0. Thoát");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập tên mới:");
                        book.setBookName(sc.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập tiêu đề:");
                        book.setTitle(sc.nextLine());
                        break;
                    case 3:
                        System.out.println("Nhập tên tác giả:");
                        book.setAuthor(sc.nextLine());
                        break;
                    case 4:
                        System.out.println("Nhập số trang sách:");
                        book.setTotalPages(Integer.parseInt(sc.nextLine()));
                        break;
                    case 5:
                        System.out.println("Nhập nội dung sách:");
                        book.setContent(sc.nextLine());
                        break;
                    case 6:
                        System.out.println("Nhập tên nhà xuất bản:");
                        book.setPublisher(sc.nextLine());
                        break;
                    case 7:
                        System.out.println("Nhập giá:");
                        book.setPrice(Double.parseDouble(sc.nextLine()));
                        break;
                    case 8:
                        System.out.println("Nhập mã loại sách:");
                        book.setTypeId(Integer.parseInt(sc.nextLine()));
                        break;
                    case 9:
                        System.out.println("Nhập trạng thái:");
                        book.setDeleted(Boolean.parseBoolean(sc.nextLine()));
                        break;
                    case 0:
                        isExist = false;
                        break;
                    default:
                        System.out.println("Sai lựa chọn");
                }
            }while(isExist);

            boolean result = BookBusiness.update(book);
            if(result){
                System.out.println("Cập nhật thành công");
            }else {
                System.out.println("Cập nhật thất bại");
            }
        }else {
            System.out.println("Không tìm thấy sách");
        }
    }
    //Xóa
    public static void deleteBook(Scanner sc){
        System.out.println("Nhập vào mã sách muốn xóa:");
        int idDelete = Integer.parseInt(sc.nextLine());
        if(BookBusiness.findById(idDelete) != null){
            boolean result = BookBusiness.delete(idDelete);
            if(result){
                System.out.println("Xóa thành công");
            }else{
                System.out.println("Xóa thất bại");
            }
        }else {
            System.out.println("Mã sách không tồn tại");
        }
    }

    //Sắp xêp theo giá giảm dần


    //Tìm kiếm
    public static void searchBook(Scanner sc){
        System.out.println("Nhập tên sách cần tìm:");
        String keyword = sc.nextLine();
        List<Book> books = BookBusiness.searchBookByNameContent(keyword);
        for (Book book : books) {
            book.displayData();
        }
    }
}


