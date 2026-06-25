-- =========================================================
-- ES234211 Programming Fundamental - Small Project
-- Simple Rock-Paper-Scissors Game with Java Swing, Login, Statistics
-- Database: MySQL
-- Rule: ONE TABLE ONLY (players)
-- =========================================================

CREATE DATABASE IF NOT EXISTS rps_project;
USE rps_project;

CREATE TABLE IF NOT EXISTS players (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    draws INT DEFAULT 0,
    score INT DEFAULT 0
);

-- Sample users for testing (username / password)
INSERT INTO players (username, password, wins, losses, draws, score) VALUES
('student1', '12345', 0, 0, 0, 0),
('student2', '12345', 0, 0, 0, 0),
('student3', '12345', 0, 0, 0, 0),
('student4', '12345', 0, 0, 0, 0),
('student5', '12345', 0, 0, 0, 0);
