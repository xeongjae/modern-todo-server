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

# ⬇️ 실행 방법 메뉴얼

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

## 🔌 API 엔드포인트

### 기본 URL: `http://localhost:8080/api`

- **GET** `/todos` - 할일 목록 조회
- **POST** `/todos` - 새 할일 생성
- **PUT** `/todos/{id}` - 할일 수정
- **DELETE** `/todos/{id}` - 할일 삭제
- **PATCH** `/todos/{id}/toggle` - 완료 상태 토글
- **GET** `/todos/stats` - 우선순위별 통계

<br />

## 📚 API 문서

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`

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

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
  
<br />
<br />

 # 🔌 API 엔드포인트

### 기본 URL: `http://localhost:8080/api`

- **GET** `/todos` - 할일 목록 조회
- **POST** `/todos` - 새 할일 생성
- **PUT** `/todos/{id}` - 할일 수정
- **DELETE** `/todos/{id}` - 할일 삭제
- **PATCH** `/todos/{id}/toggle` - 완료 상태 토글
- **GET** `/todos/stats` - 우선순위별 통계


<br />
<br />


# ⚙️ 사용 기술

| 구분         | 기술                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| ------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Frontend** | <img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black"> <img src="https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white"> <img src="https://img.shields.io/badge/Material_UI-0081CB?style=for-the-badge&logo=mui&logoColor=white"> <img src="https://img.shields.io/badge/Vite-646CFF?style=for-the-badge&logo=vite&logoColor=white"> <img src="https://img.shields.io/badge/Sass-CC6699?style=for-the-badge&logo=sass&logoColor=white">                  |
| **Backend**  | <img src="https://img.shields.io/badge/Node.js-339933?style=for-the-badge&logo=Node.js&logoColor=white"> <img src="https://img.shields.io/badge/Express-000000?style=for-the-badge&logo=express&logoColor=white"> <img src="https://img.shields.io/badge/Puppeteer-40B5A4?style=for-the-badge&logo=Puppeteer&logoColor=white"> <img src="https://img.shields.io/badge/Google_Gemini-4285F4?style=for-the-badge&logo=google&logoColor=white"> <img src="https://img.shields.io/badge/Render-46E3B7?style=for-the-badge&logo=render&logoColor=white"> |

<br />
<br />

## 🗂️ 폴더 구조

```
src/
├── main/
│   ├── java/com/todoapp/backend/
│   │   ├── config/          # 설정 클래스들
│   │   ├── controller/      # REST API 컨트롤러
│   │   ├── dto/            # 데이터 전송 객체
│   │   ├── entity/         # JPA 엔티티
│   │   ├── repository/     # 데이터 접근 계층
│   │   ├── service/        # 비즈니스 로직
│   │   └── TodoBackendApplication.java
│   └── resources/
│       └── application.properties
└── test/                   # 테스트 코드
```

<br />
<br />
