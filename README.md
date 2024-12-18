# 2차 프로젝트: 쇼핑몰

## 담당
부팀장

## 주요 기능

### 1. 회원가입
- **유효성 검사**: 
  - 비밀번호에 특수문자가 포함되어 있는지 검사
  - 비밀번호 일치 여부 확인
- **주소 입력**: 카카오 주소 라이브러리를 사용하여 주소 입력 기능 제공

### 2. 로그인
- **소셜 로그인**: 카카오, 네이버 등 소셜 로그인 기능 제공
- **일반 회원 로그인**: 이메일과 비밀번호를 이용한 일반 회원 로그인 지원

### 3. 장바구니
- **장바구니 추가**: 쇼핑몰 상세 페이지에서 장바구니에 상품 추가 가능
- **수량 및 금액 조절**: 장바구니 페이지에서 수량 변경 및 금액 자동 업데이트
- **삭제 기능**: 장바구니 내 상품 삭제 기능 제공
- **결제 페이지로 이동**: 장바구니에서 총 금액 합산 후 결제 페이지로 이동

### 4. 결제
- **토스 결제 API**: 토스 결제 API를 이용하여 안전한 결제 시스템 구현
- **전체 결제 및 단건 결제**: 장바구니 페이지에서 전체 결제 및 상세 페이지에서 단건 결제 연결 완료

### 5. 네이버웍스_조직배치연동
- **네이버웍스 조직배치 연동**: 쇼핑몰 관리자 페이지에서 네이버웍스 조직배치 연동을 활용하여 조직도 추가 기능 제공

## 기술 스택
- **프론트엔드**: HTML, CSS, JavaScript, Ajax
- **백엔드**:Java, JPA
- **데이터베이스**: MariaDB
- **결제 시스템**: Toss API
- **기타**: Kakao Address API, Naver Works API



