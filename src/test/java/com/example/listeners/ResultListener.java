package com.example.listeners;

import com.example.db.DatabaseUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;

/** Saves each test's outcome to MySQL (only when DB_* environment variables are set). */
public class ResultListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        DatabaseUtil.saveResult(result.getName(), "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        DatabaseUtil.saveResult(result.getName(), "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        DatabaseUtil.saveResult(result.getName(), "SKIP");
    }
}
