package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC helper for storing test results in MySQL.
 * Configure DB_URL, DB_USER and DB_PASSWORD environment variables to enable it.
 * When they are not set, database logging is skipped and the tests still run normally.
 */
public final class DatabaseUtil {
    private DatabaseUtil() {}

    private static final String CREATE_TABLE =
        "CREATE TABLE IF NOT EXISTS test_run ("
        + "id BIGINT PRIMARY KEY AUTO_INCREMENT, "
        + "test_name VARCHAR(150) NOT NULL, "
        + "result VARCHAR(20) NOT NULL, "
        + "executed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)";

    /** True when all three environment variables are set. */
    public static boolean isConfigured() {
        return System.getenv("DB_URL") != null
            && System.getenv("DB_USER") != null
            && System.getenv("DB_PASSWORD") != null;
    }

    public static Connection openConnection() throws SQLException {
        if (!isConfigured()) {
            throw new IllegalStateException(
                "Set DB_URL, DB_USER and DB_PASSWORD environment variables first.");
        }
        return DriverManager.getConnection(
            System.getenv("DB_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
    }

    /**
     * Saves one test result (PASS / FAIL / SKIP). Never throws: a database problem
     * must not make a good test fail.
     */
    public static void saveResult(String testName, String result) {
        if (!isConfigured()) {
            return;
        }
        try (Connection conn = openConnection()) {
            try (Statement st = conn.createStatement()) {
                st.execute(CREATE_TABLE);
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO test_run (test_name, result) VALUES (?, ?)")) {
                ps.setString(1, testName);
                ps.setString(2, result);
                ps.executeUpdate();
            }
        } catch (SQLException | IllegalStateException e) {
            System.err.println("[DB] Could not save result for " + testName + ": " + e.getMessage());
        }
    }

    /** Returns the latest saved result for a test, or null if none. Used for validation. */
    public static String getLatestResult(String testName) throws SQLException {
        try (Connection conn = openConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT result FROM test_run WHERE test_name = ? ORDER BY id DESC LIMIT 1")) {
            ps.setString(1, testName);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getString(1) : null;
            }
        }
    }
}
