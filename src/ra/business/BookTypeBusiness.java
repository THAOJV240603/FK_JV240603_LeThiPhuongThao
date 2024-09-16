package ra.business;

import ra.entity.BookType;
import ra.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookTypeBusiness {
    //Lấy về ds
    public static List<BookType> getBookTypes() {
        List<BookType> bookTypes = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement statement = connection.prepareStatement("select * from BookType");
            //PreparedStatement ps = connection.prepareStatement("select * from BookType order by id desc");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                BookType bookType = new BookType();
                bookType.setTypeId(resultSet.getInt("TypeId"));
                bookType.setTypeName(resultSet.getString("TypeName"));
                bookType.setDescription(resultSet.getString("Description"));
                bookType.setDeleted(resultSet.getBoolean("IsDeleted"));
                bookTypes.add(bookType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return bookTypes;
    }

    //In ds
    public static BookType findById(int typeId) {
        BookType bookType = null;
        Connection connection = null;
        try{
            connection = ConnectionDB.openConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * from BookType where TypeId = ?");
            statement.setInt(1, typeId);
            ResultSet resultSet = statement.executeQuery();
            bookType = new BookType();
            int count = 0;
            while (resultSet.next()) {
                count ++;
                bookType.setTypeId(resultSet.getInt("TypeId"));
                bookType.setTypeName(resultSet.getString("TypeName"));
                bookType.setDescription(resultSet.getString("Description"));
                bookType.setDeleted(resultSet.getBoolean("IsDeleted"));
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
        return bookType;
    }

    //Thêm mới
    public static boolean create(BookType bookType) {
        Connection connection = null;
        try{
            connection = ConnectionDB.openConnection();

            String sql = "insert into BookType (TypeId, TypeName, Description, IsDeleted) values (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, bookType.getTypeId());
            statement.setString(2, bookType.getTypeName());
            statement.setString(3, bookType.getDescription());
            statement.setBoolean(4, bookType.isDeleted());
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
    public static boolean update(BookType bookType) {
        Connection connection = null;
        try{
            connection = ConnectionDB.openConnection();
            String sql = "Update BookType set TypeName = ?, Description = ?, IsDeleted = ? where TypeId = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, bookType.getTypeName());
            statement.setString(2, bookType.getDescription());
            statement.setBoolean(3, bookType.isDeleted());
            statement.setInt(4, bookType.getTypeId());
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
            String sql = "delete from BookType where TypeId = ?";

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
}
