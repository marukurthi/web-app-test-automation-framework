# Web Application Test Automation Framework

A beginner-friendly Java + Selenium WebDriver + TestNG + Maven project. The tests use the public-internet demo site **https://www.saucedemo.com/** and do not require a local application.

## Requirements
- Java JDK 17 or newer
- Maven 3.8+
- Chrome browser
- Internet access on the first run (Maven dependencies and Selenium Manager browser driver setup)

Selenium Manager normally downloads/manages the compatible browser driver automatically.

## Run
Open a terminal in this folder and run:

```bash
mvn clean test
```

TestNG/Surefire reports are written under `target/surefire-reports/`.

## Tests included
- Valid login shows the products page
- Invalid password shows an error
- Logout returns to the login page
- Empty username/password shows a validation error

Demo credentials for SauceDemo (public demo site only):
- Username: `standard_user`
- Password: `secret_sauce`

## Headless mode
```bash
mvn clean test -Dheadless=true
```

## MySQL result logging (optional)
After every test, `ResultListener` saves the test name and result (PASS / FAIL / SKIP) to the `test_run` table in MySQL through JDBC (`DatabaseUtil`).
If the database variables are not set, logging is skipped and the tests run normally.

1. Create the database and table: run `src/test/resources/sql/schema.sql` in MySQL.
2. Set environment variables (Windows cmd):
```bat
set DB_URL=jdbc:mysql://localhost:3306/automation_demo
set DB_USER=root
set DB_PASSWORD=your_password
```
3. Run `mvn clean test`, then check the results:
```sql
SELECT * FROM test_run ORDER BY id DESC;
```
4. Optional database check: `mvn -Dtest=DatabaseTests test` (skipped when the variables are not set).

## Project structure
```text
src/test/java/com/example/
  base/BaseTest.java
  pages/LoginPage.java
  tests/LoginTests.java
  tests/FormValidationTests.java
  tests/DatabaseTests.java
  listeners/ResultListener.java
  db/DatabaseUtil.java
src/test/resources/sql/schema.sql
testng.xml
pom.xml
```

## Troubleshooting
- `mvn` not recognized: install Maven and add its `bin` folder to PATH, or open the project in IntelliJ IDEA and run the Maven `test` goal.
- Browser driver error: update Chrome, check internet access, then retry `mvn clean test`.
- Demo site unavailable: try again later; it is a third-party demo website.
- To run a single test class: `mvn -Dtest=LoginTests test`

## Resume guidance
Only describe the project as completed after you have run it, reviewed the reports, and understand the code. Do not claim a test count or pass rate until you have executed it.
