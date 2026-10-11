-- Optional example schema for local learning only.
-- This is not required to run the Selenium tests.
CREATE DATABASE IF NOT EXISTS automation_demo;
USE automation_demo;

CREATE TABLE IF NOT EXISTS test_run (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    test_name VARCHAR(150) NOT NULL,
    result VARCHAR(20) NOT NULL,
    executed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Example:
-- INSERT INTO test_run (test_name, result) VALUES ('validLoginShowsProducts', 'PASS');
-- SELECT * FROM test_run;
