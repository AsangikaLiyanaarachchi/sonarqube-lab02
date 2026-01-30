package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {

    // SECURITY ISSUE (still exists – shown for learning purpose)
    private String password = "admin123";

    // FIXED:
    // - Use try-with-resources (Connection closed automatically)
    // - Use try-with-resources (Statement closed automatically)
    // - Replace generic Exception with SQLException
    // - Fix SQL Injection by using PreparedStatement
    public void findUser(String username) throws SQLException {

        String sql = "SELECT * FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(
                     "jdbc:mysql://localhost/db", "root", password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.executeQuery();
        }
    }

    // EVEN WORSE before – now fixed in the same way
    // FIXED:
    // - try-with-resources for Connection
    // - try-with-resources for Statement
    // - specific exception (SQLException)
    // - SQL injection removed
    public void deleteUser(String username) throws SQLException {

        String sql = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(
                     "jdbc:mysql://localhost/db", "root", password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.executeUpdate();
        }
    }

    // (This is still a code smell – unused method)
    public void notUsed() {
        System.out.println("I am never called");
    }
}
