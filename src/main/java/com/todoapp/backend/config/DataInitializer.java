package com.todoapp.backend.config;

import com.todoapp.backend.entity.Todo;
import com.todoapp.backend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TodoRepository todoRepository;

    @Autowired
    public DataInitializer(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (todoRepository.count() == 0) {
            System.out.println("초기 데이터를 설정하고 있습니다...");
            createSampleTodos();
            System.out.println("초기 데이터 설정이 완료되었습니다.");
        } else {
            System.out.println("데이터베이스에 이미 할 일이 존재합니다. 초기화를 건너뜁니다.");
        }
    }

    private void createSampleTodos() {
        Todo todo1 = new Todo(
            "득점 연습", 
            "왼발 슈팅 정확도 높이기 - 하루 100개", 
            Todo.Priority.HIGH, 
            false
        );
        todoRepository.save(todo1);

        Todo todo2 = new Todo(
            "팬들과 소통", 
            "SNS로 팬들과 인사하고 응원 감사하기", 
            Todo.Priority.LOW, 
            false
        );
        todoRepository.save(todo2);

        Todo todo3 = new Todo(
            "체력 훈련", 
            "유산소 운동 30분 + 근력 운동 20분", 
            Todo.Priority.HIGH, 
            false
        );
        todoRepository.save(todo3);

        Todo todo4 = new Todo(
            "영어 공부", 
            "인터뷰 대비 영어 표현 연습하기", 
            Todo.Priority.MEDIUM, 
            false
        );
        todoRepository.save(todo4);

        Todo todo5 = new Todo(
            "가족과 시간 보내기", 
            "집에 있는 시간 늘리고 가족과 대화하기", 
            Todo.Priority.LOW, 
            false
        );
        todoRepository.save(todo5);

        Todo todo6 = new Todo(
            "경기 분석", 
            "다음 상대팀 경기 영상 분석하고 전술 연구하기", 
            Todo.Priority.HIGH, 
            false
        );
        todoRepository.save(todo6);

        Todo todo7 = new Todo(
            "부상 예방", 
            "스트레칭과 마사지로 몸 관리하기", 
            Todo.Priority.MEDIUM, 
            false
        );
        todoRepository.save(todo7);

        Todo todo8 = new Todo(
            "차량 정리", 
            "차 안 청소하고 정리하기", 
            Todo.Priority.LOW, 
            false
        );
        todoRepository.save(todo8);

        Todo todo9 = new Todo(
            "패스 연습", 
            "팀원들과 패스 정확도 향상 훈련", 
            Todo.Priority.MEDIUM, 
            false
        );
        todoRepository.save(todo9);

        Todo todo10 = new Todo(
            "취미 활동", 
            "영화 보면서 스트레스 해소하기", 
            Todo.Priority.LOW, 
            false
        );
        todoRepository.save(todo10);

        Todo todo11 = new Todo(
            "멘탈 관리", 
            "명상과 호흡법으로 집중력 향상하기", 
            Todo.Priority.MEDIUM, 
            false
        );
        todoRepository.save(todo11);

        Todo completedTodo = new Todo(
            "아침 식사", 
            "영양사가 준비한 아침 식사 먹기", 
            Todo.Priority.MEDIUM, 
            true
        );
        todoRepository.save(completedTodo);

        System.out.println("총 " + todoRepository.count() + "개의 할 일이 생성되었습니다.");
    }
}