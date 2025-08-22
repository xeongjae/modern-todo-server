# 모던투두 - 할 일 관리 애플리케이션 (서버)

<br />

<p align="center">
<img src="https://github.com/xeongjae/modern-todo-client/raw/main/public/doFavicon.png" alt="로고" width="200"/>
</p>

<p align="center">
  <strong>
 효율적인 일정 관리를 도와주는 할 일 관리 애플리케이션입니다.
  </strong>
</p>

<br />
<br />

# 📌 실행 방법 메뉴얼

<br />

- **Java 17** 이상
- **Maven 3.6** 이상
- **MySQL 8.0** 이상

<br />

## 🚀 실행 방법

### 1. 프로젝트 클론

```bash
git clone https://github.com/xeongjae/modern-todo-server.git
cd modern-todo-server
```

### 2. 데이터베이스 설정

#### MySQL 데이터베이스 생성

```bash
# MySQL 접속
# Password: 라고 뜨면 엔터 누르시면 됩니다
# (별도로 패스워드를 설정하지 않았기 때문)
mysql -u root -p


# 데이터베이스 생성
CREATE DATABASE todoapp CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE todoapp;
exit;
```

#### DB 스키마 및 샘플 데이터 적용

```bash
# 프로젝트 루트에서
mysql -u root -p todoapp < database_schema.sql
```

#### 데이터베이스 연결 정보 확인

`src/main/resources/application.properties`에서 다음 정보 확인:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todoapp
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. 의존성 설치 및 빌드

```bash
mvn clean install
```

### 4. 개발 서버 실행

```bash
mvn spring-boot:run
```

애플리케이션이 `http://localhost:8080`에서 실행됩니다.

<br />
<br />

## 🧪 테스트 실행

```bash
# 전체 테스트 실행
mvn test
```

<br />
<br />

## 🔄 데이터베이스 초기화

샘플 데이터를 다시 생성하려면:

```bash
# MySQL에서 테이블 데이터 삭제
mysql -u root -p todoapp -e "TRUNCATE TABLE todos;"

# 서버 재시작 (샘플 데이터 자동 생성)
mvn spring-boot:run
```

<br />
<br />

# 📚 API 명세서 (Swagger)

- http://localhost:8080/swagger-ui.html

<br />
<br />

# 🔌 API 엔드포인트

- **API 엔드포인트**: `http://localhost:8080/api`

- **GET** `/todos` - 할일 목록 조회
- **POST** `/todos` - 새 할일 생성
- **PUT** `/todos/{id}` - 할일 수정
- **DELETE** `/todos/{id}` - 할일 삭제
- **PATCH** `/todos/{id}/toggle` - 완료 상태 토글
- **GET** `/todos/stats` - 우선순위별 통계

<br />
<br />

# ⚙️ 사용 기술

| 구분         | 기술                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| ------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Frontend** | <img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black"> <img src="https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white"> <img src="https://img.shields.io/badge/Material_UI-0081CB?style=for-the-badge&logo=mui&logoColor=white"> <img src="https://img.shields.io/badge/Vite-646CFF?style=for-the-badge&logo=vite&logoColor=white"> <img src="https://img.shields.io/badge/Sass-CC6699?style=for-the-badge&logo=sass&logoColor=white">                     |
| **Backend**  | <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"> <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white"> |

<br />
<br />

## 🗂️ 프로젝트 구조

```
modern-todo-server/
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/com/todoapp/backend/
│   │   │   ├── 📁 config/                    # 애플리케이션 설정
│   │   │   │   ├── DataInitializer.java      # 샘플 데이터 초기화
│   │   │   │   ├── SwaggerConfig.java        # API 문서 설정
│   │   │   │   └── WebConfig.java            # CORS 설정
│   │   │   ├── 📁 controller/                # REST API 엔드포인트
│   │   │   │   └── TodoController.java       # 할일 CRUD API
│   │   │   ├── 📁 dto/                       # 데이터 전송 객체
│   │   │   │   └── TodoDto.java              # API 요청/응답 데이터 구조
│   │   │   ├── 📁 entity/                    # JPA 데이터베이스 모델
│   │   │   │   └── Todo.java                 # 할일 테이블 엔티티
│   │   │   ├── 📁 repository/                # 데이터 접근 계층
│   │   │   │   └── TodoRepository.java       # 데이터베이스 쿼리 메서드
│   │   │   ├── 📁 service/                   # 비즈니스 로직
│   │   │   │   └── TodoService.java          # 할일 관리 서비스
│   │   │   └── 📄 TodoBackendApplication.java # 메인 애플리케이션 클래스
│   │   └── 📁 resources/                     # 설정 파일 및 리소스
│   │       ├── application.properties         # 데이터베이스 연결 설정
│   │       └── application-dev.properties     # 개발 환경 설정
│   └── 📁 test/                              # 테스트 코드
│       └── 📁 java/com/todoapp/backend/
│           ├── TodoControllerTest.java        # 컨트롤러 테스트
│           └── TodoServiceTest.java           # 서비스 테스트
├── 📁 target/                                # 빌드 결과물 (자동 생성)
├── 📄 pom.xml                                # Maven 프로젝트 설정
├── 📄 database_schema.sql                    # 데이터베이스 스키마 및 샘플 데이터
└── 📄 README.md                              # 프로젝트 설명서
```

### 📋 각 계층별 역할

#### **🏗️ Controller Layer (컨트롤러 계층)**

- HTTP 요청/응답 처리
- API 엔드포인트 정의
- 클라이언트와의 통신 담당

#### **⚙️ Service Layer (서비스 계층)**

- 비즈니스 로직 처리
- 데이터 검증 및 가공
- 트랜잭션 관리

#### **🗄️ Repository Layer (저장소 계층)**

- 데이터베이스 접근
- SQL 쿼리 실행
- 데이터 CRUD 작업

#### **📊 Entity Layer (엔티티 계층)**

- 데이터베이스 테이블 구조 정의
- JPA 매핑 정보
- 데이터 모델링

#### **📦 DTO Layer (데이터 전송 계층)**

- API 요청/응답 데이터 구조
- 클라이언트와 서버 간 데이터 형식
- 데이터 검증 규칙

<br />
<br />
