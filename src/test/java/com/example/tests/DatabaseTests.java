package com.example.tests;

import com.example.db.DatabaseUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

/**
 * Database validation test. Not part of the default suite.
 * Run with: mvn -Dtest=DatabaseTests test   (after setting DB_URL, DB_USER, DB_PASSWORD)
 */
public class DatabaseTests {

    @Test(description = "A saved result can be read back from MySQL")
    public void savedResultCanBeReadBack() throws Exception {
        if (!DatabaseUtil.isConfigured()) {
            throw new SkipException("DB_URL, DB_USER and DB_PASSWORD are not set.");
        }
        String name = "dbValidation_" + System.currentTimeMillis();
        DatabaseUtil.saveResult(name, "PASS");
        Assert.assertEquals(DatabaseUtil.getLatestResult(name), "PASS",
                "The result written to MySQL should be readable.");
    }
}
