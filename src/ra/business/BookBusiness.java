package ra.business;

import ra.entity.Book;
import ra.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBusiness {
    //Lấy về ds
    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement statement = connection.prepareStatement("select * from Book");
            //PreparedStatement ps = connection.prepareStatement("select * from Book order by id desc");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setTypeId(resultSet.getInt("BookId"));
                book.setBookName(resultSet.getString("BookName"));
                book.setTitle(resultSet.getString("Title"));
                book.setAuthor(resultSet.getString("Author"));
                book.setTotalPages(resultSet.getInt("TotalPages"));
                book.setContent(resultSet.getString("Content"));
                book.setPublisher(resultSet.getString("Publisher"));
                book.setPrice(resultSet.getDouble("Price"));
                book.setTypeId(resultSet.getInt("TypeId"));
                book.setDeleted(resultSet.getBoolean("IsDeleted"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return books;
    }

    //In ds
    public static Book findById(int bookId) {
        Book book = null;
        Connection connection = null;
        try{
            connection = ConnectionDB.openConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * from Book where BookId = ?");
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            book = new Book();
            int count = 0;
            while (resultSet.next()) {
                count ++;
                book.setBookId(resultSet.getInt("BookId"));
                book.setBookName(resultSet.getString("BookName"));
                book.setTitle(resultSet.getString("Title"));
                book.setAuthor(resultSet.getString("Author"));
                book.setTotalPages(resultSet.getInt("TotalPages"));
                book.setContent(resultSet.getString("Content"));
                book.setPublisher(resultSet.getString("Publisher"));
                book.setPrice(resultSet.getDouble("Price"));
                book.setTypeId(resultSet.getInt("TypeId"));
                book.setDeleted(resultSet.getBoolean("IsDeleted"));
            }
            System.out.println(count);
            if(count == 0) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return book;
    }

    //Thêm mới
    public static boolean create(Book book) {
        Connection connection = null;
        try{
            connection = ConnectionDB.openConnection();

            String sql = "insert into Book(BookId,BookName,Title,Author,TotalPages,Content,Publisher,Price,TypeId,IsDelete) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, book.getBookId());
            statement.setString(2, book.getBookName());
            statement.setString(3, book.getTitle());
            statement.setString(4, book.getAuthor());
            statement.setInt(5, book.getTotalPages());
            statement.setString(6, book.getContent());
            statement.setString(7, book.getPublisher());
            statement.setDouble(8, book.getPrice());
            statement.setInt(9, book.getTypeId());
            statement.setBoolean(10, book.isDeleted());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    //Sửa
    public static boolean update(Book book) {
        Connection connection = null;
        try{
            connection = ConnectionDB.openConnection();
            String sql = "Update Book set BookName = ?, Title = ?, Author = ?, TotalPages = ?, Content = ?, Publisher =?, Price = ?, TypeId = ?, IsDeleted = ? where BookId = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getBookName());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setInt(4, book.getTotalPages());
            statement.setString(5, book.getContent());
            statement.setString(6, book.getPublisher());
            statement.setDouble(7, book.getPrice());
            statement.setInt(8, book.getTypeId());
            statement.setBoolean(9, book.isDeleted());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    //Xóa
    public static boolean delete(int typeId) {
        Connection connection = null;
        try{
            connection = ConnectionDB.openConnection();
            String sql = "delete from Book where BookId = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, typeId);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    //ds sách theo giá giảm dần
    /*
    public static Book sortByPrice(double price) {
        Book book = null;
        Connection connection = null;
        try{
            connection = ConnectionDB.openConnection();
            PreparedStatement statement = connection.prepareStatement("select * from Book order by Price desc");
            statement.setDouble(1, price);
            ResultSet resultSet = statement.executeQuery();
            book = new Book();
            int count = 0;
            while (resultSet.next()) {
                count ++;
                book.setBookId(resultSet.getInt("BookId"));
                book.setBookName(resultSet.getString("BookName"));
                book.setTitle(resultSet.getString("Title"));
                book.setAuthor(resultSet.getString("Author"));
                book.setTotalPages(resultSet.getInt("TotalPages"));
                book.setContent(resultSet.getString("Content"));
                book.setPublisher(resultSet.getString("Publisher"));
                book.setPrice(resultSet.getDouble("Price"));
                book.setTypeId(resultSet.getInt("TypeId"));
                book.setDeleted(resultSet.getBoolean("IsDeleted"));
            }
            System.out.println(count);
            if(count == 0) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return book;
    }
    */

    //Tìm kiếm
    public static List<Book> searchBookByNameContent(String keyword) {
        List<Book> books = new ArrayList<>();
        Connection connection = null;
        try{
            connection = ConnectionDB.openConnection();
            String sql = "select * from Book where BookName, Content like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt("BookId"));
                book.setBookName(resultSet.getString("BookName"));
                book.setTitle(resultSet.getString("Title"));
                book.setAuthor(resultSet.getString("Author"));
                book.setTotalPages(resultSet.getInt("TotalPages"));
                book.setContent(resultSet.getString("Content"));
                book.setPublisher(resultSet.getString("Publisher"));
                book.setPrice(resultSet.getDouble("Price"));
                book.setTypeId(resultSet.getInt("TypeId"));
                book.setDeleted(resultSet.getBoolean("IsDeleted"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return books;
    }
}
