package ra.entity;

import java.util.Scanner;

public class BookType implements IBookManagement{
    private int TypeId;
    private String TypeName;
    private String Description;
    private boolean IsDeleted;

    //Constructor
    public BookType() {
    }

    public BookType(int typeId, String typeName, String description, boolean isDeleted) {
        TypeId = typeId;
        TypeName = typeName;
        Description = description;
        IsDeleted = isDeleted;
    }

    //Getter, setter
    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int typeId) {
        TypeId = typeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }

    @Override
    public void inputData(Scanner scanner) {
        //System.out.println("Mã loại sách:");
        //this.TypeId = Integer.parseInt(scanner.nextLine());
        System.out.println("Tên loại sách:");
        this.TypeName = scanner.nextLine();
        System.out.println("Mô tả:");
        this.Description = scanner.nextLine();
        System.out.println("Trạng thái:");
        this.IsDeleted = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void displayData() {
        //System.out.println("----- Thông tin loại sách -----");
        System.out.println("Mã loại sách: " + this.TypeId);
        System.out.println("Tên loại sách: " + this.TypeName);
        System.out.println("Mô tả loại sách: " + this.Description);
        System.out.println("Trạng thái: " + (this.IsDeleted ? "Chưa xóa" : "Đã xóa"));
        System.out.println("\n");
    }
}
