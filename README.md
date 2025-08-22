# 🚀 Smart Todo App - Backend

Spring Boot 3를 사용한 현대적인 할 일 관리 API 서버입니다.

## ✨ 주요 기능

### 🎯 핵심 기능
- **할 일 관리**: 추가, 조회, 수정, 삭제 (CRUD)
- **우선순위 관리**: 높음(HIGH), 보통(MEDIUM), 낮음(LOW)
- **마감일 관리**: 날짜별 할 일 관리 및 연체 확인
- **완료 상태 관리**: 완료/미완료 상태 토글

### 🚀 추가 기능
- **할 일 공유**: 이메일을 통한 할 일 공유
- **통계 API**: 완료율, 우선순위별 현황 등
- **검색 기능**: 제목/설명 기반 키워드 검색
- **필터링**: 우선순위, 완료상태별 조회
- **정렬**: 다양한 기준으로 할 일 정렬

## 🚀 실행 방법

### 1. 필수 요구사항
- Java 17+
- Maven 3.6+
- MySQL 8.0+ (프로덕션)

### 2. 데이터베이스 설정

#### 개발 환경 (H2 인메모리)
```properties
# application-dev.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
```

#### 프로덕션 환경 (MySQL)
```properties
# application-prod.properties
spring.datasource.url=jdbc:mysql://localhost:3306/todoapp
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 3. 애플리케이션 실행

#### 개발 환경
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

#### 프로덕션 환경
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### 4. 빌드 및 JAR 실행
```bash
mvn clean package
java -jar target/todo-backend-0.0.1-SNAPSHOT.jar
```

애플리케이션이 `http://localhost:8080`에서 실행됩니다.

## 🔧 기술 스택

### 🚀 Spring Framework
- **Spring Boot 3.2.1** - 최신 Spring Boot 버전
- **Spring Data JPA** - 데이터 접근 계층
- **Spring Web** - RESTful API 구현
- **Spring Validation** - 입력 데이터 검증

### 🗄️ 데이터베이스
- **H2 Database** - 개발용 인메모리 데이터베이스
- **MySQL 8** - 프로덕션 데이터베이스
- **Hibernate** - ORM 프레임워크

### 🛠️ 개발 도구
- **Maven** - 프로젝트 빌드 및 의존성 관리
- **Lombok** - 보일러플레이트 코드 제거
- **Spring Boot DevTools** - 개발 편의성 향상

### 📚 API 문서화
- **Springdoc OpenAPI 3** - Swagger UI 자동 생성
- **API 명세서** - 상세한 엔드포인트 설명

### 🧪 테스트
- **JUnit 5** - 단위 테스트 프레임워크
- **Mockito** - 모킹 프레임워크
- **Spring Boot Test** - 통합 테스트

## 📱 주요 컴포넌트

### 🎯 TodoController (REST API)
- RESTful 엔드포인트 제공
- HTTP 메서드별 CRUD 작업
- 요청/응답 데이터 검증
- 에러 처리 및 예외 관리

### 🔧 TodoService (비즈니스 로직)
- 할 일 관련 비즈니스 로직 처리
- 데이터 검증 및 변환
- 트랜잭션 관리
- 비즈니스 규칙 적용

### 🗄️ TodoRepository (데이터 접근)
- Spring Data JPA 기반 데이터 접근
- 커스텀 쿼리 메서드
- 페이징 및 정렬 지원
- 성능 최적화된 쿼리

### 📊 Todo Entity (데이터 모델)
- JPA 엔티티 정의
- 데이터베이스 스키마 매핑
- 비즈니스 로직 메서드
- 감사 정보 관리

## 🔗 API 엔드포인트

### 📋 할 일 관리
| 메서드 | 엔드포인트 | 설명 |
|--------|------------|------|
| GET | `/api/todos` | 모든 할 일 조회 |
| GET | `/api/todos/{id}` | 특정 할 일 조회 |
| POST | `/api/todos` | 할 일 생성 |
| PUT | `/api/todos/{id}` | 할 일 수정 |
| DELETE | `/api/todos/{id}` | 할 일 삭제 |
| PATCH | `/api/todos/{id}/toggle` | 완료 상태 토글 |

### 🚀 추가 기능
| 메서드 | 엔드포인트 | 설명 |
|--------|------------|------|
| POST | `/api/todos/share` | 할 일 공유 |
| GET | `/api/todos/stats` | 통계 조회 |
| GET | `/api/todos/search` | 키워드 검색 |
| GET | `/api/todos/priority/{priority}` | 우선순위별 조회 |
| GET | `/api/todos/completed/{completed}` | 완료상태별 조회 |
| GET | `/api/todos/today-due` | 오늘 마감 할 일 |
| GET | `/api/todos/overdue` | 연체된 할 일 |

## 🗄️ 데이터베이스 스키마

### 📋 todos 테이블
```sql
CREATE TABLE todos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(500) NOT NULL COMMENT '할 일 제목',
    description TEXT COMMENT '할 일 상세 설명',
    completed BOOLEAN DEFAULT FALSE NOT NULL COMMENT '완료 여부',
    priority ENUM('LOW', 'MEDIUM', 'HIGH') DEFAULT 'MEDIUM' NOT NULL COMMENT '우선순위',
    due_date TIMESTAMP NULL COMMENT '마감일',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'
);
```

### 📧 todo_shared_with 테이블
```sql
CREATE TABLE todo_shared_with (
    todo_id BIGINT NOT NULL,
    email VARCHAR(255) NOT NULL COMMENT '공유 대상 이메일',
    PRIMARY KEY (todo_id, email),
    FOREIGN KEY (todo_id) REFERENCES todos(id) ON DELETE CASCADE
);
```

## 🧪 테스트

### 🧪 단위 테스트
```bash
mvn test
```

### 📊 테스트 커버리지
```bash
mvn test jacoco:report
```

### 🔍 테스트 구조
- **TodoServiceTest**: 서비스 계층 단위 테스트
- **TodoControllerTest**: 컨트롤러 계층 통합 테스트

## 📚 API 문서

### 🔍 Swagger UI
- URL: `http://localhost:8080/swagger-ui.html`
- API 명세서 자동 생성
- 실시간 API 테스트 가능

### 📖 OpenAPI 3 명세
- JSON 형식: `http://localhost:8080/v3/api-docs`
- YAML 형식: `http://localhost:8080/v3/api-docs.yaml`

## 🚀 주요 라이브러리 사용 이유

### 🚀 Spring Boot 3
- **최신 Java 기능**: Java 17+ 지원
- **자동 설정**: 최소한의 설정으로 애플리케이션 실행
- **내장 서버**: 별도 웹 서버 설치 불필요
- **프로덕션 준비**: 모니터링, 메트릭, 헬스체크

### 🗄️ Spring Data JPA
- **Repository 패턴**: 데이터 접근 계층 추상화
- **쿼리 메서드**: 메서드명으로 쿼리 자동 생성
- **페이징/정렬**: 대용량 데이터 처리 최적화
- **트랜잭션 관리**: 선언적 트랜잭션 지원

### 🔒 Spring Validation
- **Bean Validation**: JSR-303 표준 준수
- **커스텀 검증**: 비즈니스 규칙 검증
- **에러 메시지**: 다국어 지원

### 📚 Springdoc OpenAPI
- **자동 문서화**: 코드 기반 API 문서 생성
- **Swagger UI**: 직관적인 API 테스트 인터페이스
- **실시간 업데이트**: 코드 변경 시 자동 반영

### 🧪 JUnit 5 + Mockito
- **최신 테스트 기능**: JUnit 5의 향상된 기능
- **모킹**: 의존성 격리 및 테스트
- **통합 테스트**: Spring Boot Test 지원

## 🔧 설정 파일

### 📝 application.properties
```properties
# 서버 설정
server.port=8080
server.servlet.context-path=/api

# 로깅 설정
logging.level.com.todoapp=DEBUG
logging.level.org.springframework.web=DEBUG
```

### 🎯 application-dev.properties
```properties
# 개발 환경 설정
spring.profiles.active=dev
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

## 🚀 배포

### 📦 JAR 파일 생성
```bash
mvn clean package -DskipTests
```

### 🌐 실행
```bash
java -jar target/todo-backend-0.0.1-SNAPSHOT.jar
```

### 🔧 환경 변수
```bash
export SPRING_PROFILES_ACTIVE=prod
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/todoapp
export SPRING_DATASOURCE_USERNAME=your_username
export SPRING_DATASOURCE_PASSWORD=your_password
```

## 📊 모니터링

### 🔍 헬스체크
- `/actuator/health` - 애플리케이션 상태 확인

### 📈 메트릭
- `/actuator/metrics` - 성능 메트릭 조회

### 📝 로그
- 구조화된 로깅
- 로그 레벨별 출력 제어

## 🔒 보안

### 🌐 CORS 설정
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
```

## 🚀 성능 최적화

### 📦 JPA 최적화
- **지연 로딩**: 필요 시에만 데이터 로드
- **N+1 문제 해결**: JOIN FETCH 사용
- **인덱스 최적화**: 데이터베이스 인덱스 설계

### 🔄 캐싱
- **Spring Cache**: 메서드 레벨 캐싱
- **Redis**: 분산 캐싱 (선택사항)

## 📚 추가 리소스

- [Spring Boot 공식 문서](https://spring.io/projects/spring-boot)
- [Spring Data JPA 가이드](https://spring.io/guides/gs/accessing-data-jpa/)
- [Spring Boot 테스트 가이드](https://spring.io/guides/gs/testing-web/)

---

**개발자**: Smart Todo App Team  
**버전**: 1.0.0  
**최종 업데이트**: 2024년 12월