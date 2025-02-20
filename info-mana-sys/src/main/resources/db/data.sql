CREATE TABLE IF NOT EXISTS student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    grade VARCHAR(50)
);

INSERT INTO student (name, age, grade) VALUES
('John Doe', 20, 'A'),
('Jane Smith', 22, 'B');
