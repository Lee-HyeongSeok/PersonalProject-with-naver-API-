## **2021-11-09 프로젝트 진행 상황**

***

<br> 

### :pushpin: 진행 상황

- Naver API 연동
  - 네이버 아이디로 로그인 구현



***

## :round_pushpin: 필기 사항

<br> 

### :pushpin: DAO, DTO, VO, BO 차이

- **DAO(Data Access Object)**
  - 데이터 사용 기능 담당 클래스
  - 데이터베이스 접근을 위한 로직과 비즈니스 로직을 분리하기 위해 사용
  - 데이터베이스 커넥션 로직까지 설정된 경우가 있음
  - CRUD 기능 전담
- **DTO(Data Transfer Object)**
  - 데이터 저장 담당 클래스
  - Controller, Service, View 계층간 데이터 교환을 위해 사용되는 객체
  - 로직을 갖지 않는 순수한 데이터 객체
  - getter, setter 메서드만을 포함 
- **VO(Value Object)**
  - 데이터 저장 담당 클래스
  - DTO와 혼용해서 사용되지만, 값을 위해 사용되는 객체
  - 불변의 속성을 가진다.
  - 보통 getter의 기능만을 포함
- **BO(Business Object)**
  - 비즈니스 로직을 포함하는 객체
  - 비즈니스 관련 내용을 담은 VO 객체와 같다.