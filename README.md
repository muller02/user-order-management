
## 📝 프로젝트 개요
- Spring Boot + JPA 기반 REST API 프로젝트
- Product 등록, 조회, 수정, 삭제(CRUD) 기능 구현
- 로컬 MariaDB 환경에서 API 테스트 완료

## ⚙️ 주요 기능
| 기능 | 설명 |
|------|------|
| 등록 (POST) | Product 생성 후 DB 저장 |
| 조회 (GET) | 단건/전체 조회 가능 |
| 테스트 | `api-test.http`로 로컬 API 검증 가능 |

## 💻 기술 스택
- Java 17
- Spring Boot
- Spring Data JPA
- MariaDB
- Gradle

## 🔧 실행 방법

### 1. DB 설정
- 로컬 MariaDB 사용
- `src/main/resources/key.yml` 파일에 datesource 설정 필요

```yml
DB:
  USERNAME: (username 입력)
  PASSWORD: (password 입력)
```
### 2. 프로젝트 실행
```bash
# 프로젝트 클론
git clone <repository-url>
cd project-name

# 빌드 & 실행
./gradlew clean build
./gradlew bootRun

```
## 🗂️ 테스트 방법

- VS Code에서 api-test.http 파일로 API 테스트 가능
- Postman 등으로도 테스트 가능
