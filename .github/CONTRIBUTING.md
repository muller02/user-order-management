# 👋 Contributing Guide

이 문서는 프로젝트에 기여하기 위한 기본 가이드라인을 제공합니다.  
코드를 수정하거나 Pull Request를 열기 전 아래 내용을 반드시 확인해주세요.

---

## 🧱 1. 브랜치 전략
> 브랜치는 다음과 같이 사용합니다.

| 브랜치 | 설명 |
|--------|------|
| `main` | 배포용 브랜치 |
| `develop` | 개발 통합 브랜치 |
| `feature/*` | 기능 개발용 브랜치 (예: `feature/add-product-api`) |
| `fix/*` | 버그 수정 브랜치 |
| `hotfix/*` | 긴급 수정용 브랜치 |

---

## 💬 2. 커밋 컨벤션
> 모든 커밋 메시지는 아래 규칙을 따릅니다.
<type>: <subject>

ex) feat: 상품 등록 API 추가

| Type | 설명 |
|------|------|
| feat | 새로운 기능 추가 |
| fix | 버그 수정 |
| docs | 문서 수정 |
| style | 코드 포맷팅, 세미콜론 누락 등 (코드 변경 없음) |
| refactor | 코드 리팩토링 |
| test | 테스트 코드 추가 |
| chore | 빌드, 패키지, 설정 파일 등 변경 |

**예시**
```
feat: add POST and GET /product enpoints
- POST /poduct : ProductDTO 매핑, ProductService save 로직 구현

- GET /product/{id} : PathVariable로 상품 조회, ProductService getProductById  로직 구현
```