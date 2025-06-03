CREATE TABLE IF NOT EXISTS student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    grade VARCHAR(50),
    major  VARCHAR(200),
    nums BIGINT,
    password VARCHAR(255) NOT NULL -- 存储加密后的密码
);

CREATE TABLE IF NOT EXISTS teacher (
    id INT AUTO_INCREMENT PRIMARY KEY,
    work_id VARCHAR(20) NOT NULL UNIQUE, -- 教职工证件号
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100),
    title VARCHAR(50),
    is_admin BOOLEAN DEFAULT FALSE,
    password VARCHAR(255) NOT NULL -- 存储加密后的密码
);
