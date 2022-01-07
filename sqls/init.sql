CREATE DATABASE IF NOT EXISTS rs_test;

USE rs_test;

CREATE TABLE IF NOT EXISTS test_table
(
    id            BIGINT AUTO_INCREMENT,
    title         VARCHAR(255) NOT NULL,
    content       VARCHAR(255),
    author        VARCHAR(80)  NOT NULL,
    register_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_date   DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);