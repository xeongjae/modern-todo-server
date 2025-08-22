-- Todo App Database Schema & Sample Data
-- 완전한 DB 스키마 및 기초데이터 백업파일

-- 할 일 테이블
CREATE TABLE IF NOT EXISTS todos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    priority VARCHAR(10) NOT NULL DEFAULT 'MEDIUM',
    completed BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    INDEX idx_priority (priority),
    INDEX idx_completed (completed)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 기존 데이터 삭제
DELETE FROM todos;

-- 샘플 데이터 (손흥민 투두 리스트)
INSERT INTO todos (title, description, priority, completed) VALUES
('득점 연습', '왼발 슈팅 정확도 높이기 - 하루 100개', 'HIGH', false),
('팬들과 소통', 'SNS로 팬들과 인사하고 응원 감사하기', 'LOW', false),
('체력 훈련', '유산소 운동 30분 + 근력 운동 20분', 'HIGH', false),
('영어 공부', '인터뷰 대비 영어 표현 연습하기', 'MEDIUM', false),
('가족과 시간 보내기', '집에 있는 시간 늘리고 가족과 대화하기', 'LOW', false),
('경기 분석', '다음 상대팀 경기 영상 분석하고 전술 연구하기', 'HIGH', false),
('부상 예방', '스트레칭과 마사지로 몸 관리하기', 'MEDIUM', false),
('차량 정리', '차 안 청소하고 정리하기', 'LOW', false),
('패스 연습', '팀원들과 패스 정확도 향상 훈련', 'MEDIUM', false),
('취미 활동', '영화 보면서 스트레스 해소하기', 'LOW', false),
('멘탈 관리', '명상과 호흡법으로 집중력 향상하기', 'MEDIUM', false),
('아침 식사', '영양사가 준비한 아침 식사 먹기', 'MEDIUM', true);