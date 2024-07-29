create database dblybrary;
use dblybrary;

CREATE TABLE Books (
    Book_Id INT AUTO_INCREMENT PRIMARY KEY,
    Entry_Date DATE NOT NULL,
    Price FLOAT NOT NULL,
    Quantity INT NOT NULL,
    Publisher VARCHAR(100),
    `Condition` VARCHAR(20) NULL,  -- For Textbooks
    Tax FLOAT NULL  -- For ReferenceBooks
);
 
 select * from Books;

INSERT INTO Books (Entry_Date, Price, Quantity, Publisher, `Condition`, Tax) VALUES
('2024-01-15', 59.99, 10, 'NXB Giáo Dục Việt Nam', 'Sách mới', NULL),
('2024-02-10', 45.50, 5, 'NXB Kim Đồng', 'Sách cũ', NULL),
('2024-03-05', 120.00, 8, 'NXB Khoa Học Tự Nhiên', NULL, 10.00),
('2024-04-20', 75.99, 15, 'NXB Đại Học Quốc Gia', 'Sách mới', NULL),
('2024-05-30', 99.99, 12, 'NXB Chính Trị Quốc Gia', NULL, 8.00);