-- Smart Todo App Database Schema
-- MySQL 8.0+ 호환

-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS todoapp 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE todoapp;

-- 할 일 테이블
CREATE TABLE IF NOT EXISTS todos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(500) NOT NULL COMMENT '할 일 제목',
    description TEXT COMMENT '할 일 상세 설명',
    completed BOOLEAN DEFAULT FALSE NOT NULL COMMENT '완료 여부',
    priority ENUM('LOW', 'MEDIUM', 'HIGH') DEFAULT 'MEDIUM' NOT NULL COMMENT '우선순위',
    due_date TIMESTAMP NULL COMMENT '마감일',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    
    INDEX idx_todo_priority (priority),
    INDEX idx_todo_completed (completed),
    INDEX idx_todo_due_date (due_date),
    INDEX idx_todo_created_at (created_at),
    INDEX idx_todo_priority_completed (priority, completed)
) COMMENT='할 일 목록';

-- 할 일 공유 테이블
CREATE TABLE IF NOT EXISTS todo_shared_with (
    todo_id BIGINT NOT NULL,
    email VARCHAR(255) NOT NULL COMMENT '공유 대상 이메일',
    
    PRIMARY KEY (todo_id, email),
    FOREIGN KEY (todo_id) REFERENCES todos(id) ON DELETE CASCADE,
    
    INDEX idx_shared_email (email)
) COMMENT='할 일 공유 정보';

-- 샘플 할 일 데이터
INSERT INTO todos (title, description, completed, priority, due_date) VALUES
('프로젝트 계획서 작성', '새로운 프로젝트의 상세 계획서를 작성해야 합니다.', false, 'HIGH', DATE_ADD(NOW(), INTERVAL 3 DAY)),
('운동하기', '주 3회 30분씩 운동하기', false, 'MEDIUM', DATE_ADD(NOW(), INTERVAL 1 DAY)),
('책 읽기', 'Clean Code 책 읽고 정리하기', false, 'LOW', DATE_ADD(NOW(), INTERVAL 7 DAY)),
('장보기', '주말 장보기 - 야채, 과일, 생필품', false, 'MEDIUM', DATE_ADD(NOW(), INTERVAL 2 DAY)),
('친구 만나기', '오랜만에 친구들과 만나서 식사하기', false, 'LOW', DATE_ADD(NOW(), INTERVAL 5 DAY)),
('완료된 작업', '이미 완료된 할 일 예시', true, 'HIGH', DATE_SUB(NOW(), INTERVAL 1 DAY))
ON DUPLICATE KEY UPDATE title=title;

-- 통계 조회 뷰
CREATE OR REPLACE VIEW todo_stats AS
SELECT 
    COUNT(*) as total_count,
    SUM(CASE WHEN completed = true THEN 1 ELSE 0 END) as completed_count,
    SUM(CASE WHEN completed = false THEN 1 ELSE 0 END) as pending_count,
    SUM(CASE WHEN priority = 'HIGH' AND completed = false THEN 1 ELSE 0 END) as high_priority_pending,
    SUM(CASE WHEN DATE(due_date) = CURDATE() AND completed = false THEN 1 ELSE 0 END) as due_today,
    SUM(CASE WHEN due_date < NOW() AND completed = false THEN 1 ELSE 0 END) as overdue_count
FROM todos;