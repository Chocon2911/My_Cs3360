package App.Test;

import java.sql.*;

public class SQLiteExample {
    public static void main(String[] args) {
        // Đường dẫn đến file database
        String url = "jdbc:sqlite:./DataBaseCtrl/mydatabase.db";

        // Kết nối đến cơ sở dữ liệu
        try (Connection conn = DriverManager.getConnection(url))
        {
            if (conn != null)
            {
                System.out.println("Kết nối đến cơ sở dữ liệu thành công!");

                // Tạo bảng
                String createTableSQL = "CREATE TABLE IF NOT EXISTS Users "
                        + "("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "name TEXT NOT NULL, "
                        + "age INTEGER, "
                        + "email TEXT UNIQUE"
                        + ");";
                try (Statement stmt = conn.createStatement())
                {
                    stmt.execute(createTableSQL);
                    System.out.println("Bảng Users đã được tạo.");
                }

                // Chèn dữ liệu vào bảng
                String insertSQL = "INSERT INTO Users (name, age, email) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL))
                {
                    pstmt.setString(1, "John Doe");
                    pstmt.setInt(2, 30);
                    pstmt.setString(3, "johndoe@example.com");
                    pstmt.executeUpdate();
                    System.out.println("Dữ liệu đã được chèn vào bảng Users.");
                }

                // Truy vấn dữ liệu từ bảng
                String selectSQL = "SELECT id, name, age, email FROM Users";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(selectSQL)) {
                    System.out.println("Dữ liệu trong bảng Users:");
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        String email = rs.getString("email");
                        System.out.println(id + "\t" + name + "\t" + age + "\t" + email);
                    }
                }

                // Cập nhật dữ liệu trong bảng
                String updateSQL = "UPDATE Users SET age = ? WHERE name = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                    pstmt.setInt(1, 31); // Cập nhật tuổi
                    pstmt.setString(2, "John Doe"); // Cập nhật cho người có tên "John Doe"
                    pstmt.executeUpdate();
                    System.out.println("Dữ liệu đã được cập nhật.");
                }

                // Xóa dữ liệu trong bảng
                String deleteSQL = "DELETE FROM Users WHERE name = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                    pstmt.setString(1, "John Doe"); // Xóa người có tên "John Doe"
                    pstmt.executeUpdate();
                    System.out.println("Dữ liệu đã được xóa.");
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
