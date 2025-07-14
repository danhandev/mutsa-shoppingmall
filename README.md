# 🛒 mutsa-shoppingmall

Spring Boot 기반의 쇼핑몰 웹 서비스입니다.  
멋쟁이사자처럼 Week 6 과제로 진행되며, 실무적인 구조와 Git Flow 브랜치 전략을 적용해 협업 중심으로 개발됩니다.

---

## 📌 프로젝트 개요

- 상품 조회 및 검색
- 장바구니 기능
- 사용자 인증 예정
- RESTful API 설계
- DTO/Entity 분리 및 유효성 검증 적용 예정

---

## 👩‍💻 팀원

| 이름     | 역할             |
|----------|------------------|
| 김다연   | 장바구니 관련 API 개발     |
| 박서영   | 상품 관련 API 개발     |

---

## ⚙️ 기술 스택

| 구분       | 기술                             |
|------------|----------------------------------|
| Language   | Java 21                          |
| Framework  | Spring Boot 3.5.3                |
| DB         | MySQL 8, H2 (test)               |
| ORM        | Spring Data JPA                  |
| Build Tool | Gradle                           |
| 기타       | Lombok, JPA Auditing, Validation |

---

## 🧱 프로젝트 구조

```

src/
└── main/
├── java/com/mutsa/shoppingmall
│   ├── domain        # Entity 및 도메인 로직
│   ├── controller    # API 컨트롤러
│   ├── service       # 서비스 계층
│   ├── repository    # JPA 인터페이스
│   ├── dto           # 요청/응답 DTO
│   └── config        # 설정 클래스
└── resources/
├── application.properties

````

---

## 🛠️ 실행 방법

```bash
# 1. 프로젝트 클론
git clone https://github.com/danhandev/mutsa-shoppingmall.git

# 2. 브랜치 이동
git checkout develop

# 3. MySQL 로컬 서버 실행 및 DB 생성
create database shoppingmall;

# 4. application.properties 설정 (DB 계정 입력)

# 5. 프로젝트 실행
./gradlew bootRun
````

---

## 🔧 application.properties

```properties
spring.application.name=shoppingmall

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/shoppingmall?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Logging Configuration
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

---

## 🗂️ 브랜치 전략

```plaintext
main           → 실제 운영/배포 브랜치
develop        → 모든 기능 개발의 기준 브랜치
feature/기능명 → 기능 단위 개발 브랜치
```

* 모든 기능은 `feature/*`에서 개발 → `develop` 병합
* 최종 릴리스는 `develop` → `main` 병합
* PR은 GitHub에서 리뷰 후 병합

---

## API TEST 결과

<img width="614" alt="image" src="https://github.com/user-attachments/assets/2608d52d-fca0-469d-b61d-f708003f9414" />
<img width="623" alt="image" src="https://github.com/user-attachments/assets/3518c2db-7c4f-4e0f-876a-3090f09f885c" />
<img width="615" alt="image" src="https://github.com/user-attachments/assets/d2a1fe31-0e89-4abe-ba07-0fcba466fc81" />

<img width="612" alt="image" src="https://github.com/user-attachments/assets/8e08ef2e-3427-40d6-b3ed-8e72a7a50543" />
<img width="628" alt="image" src="https://github.com/user-attachments/assets/1dd3b45c-98c0-4def-9ca4-5a071045daff" />
<img width="617" alt="image" src="https://github.com/user-attachments/assets/0d504fb7-14a4-46d9-bc19-31f0852bdf78" />

---


