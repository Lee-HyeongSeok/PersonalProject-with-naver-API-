## **2021-11-15 프로젝트 진행 상황**

***

<br> 

### :pushpin: 진행 상황

- **Naver API 연동(로그인)**

  - 네이버 아이디로 로그인 구현
  - 로그인 이후 팝업창 제거, 부모 페이지 리로딩
  - 세션 유지<br> 

- **Naver 검색 API 연동(뉴스)** 

  - service 단에 존재하는 NaverNewsService 클래스를 통해 검색 API 사용

  - HttpURLConnection 클래스를 사용하여 연결 후 String형태의 결과 반환

  - String 형태의 결과는 JSON object 형태로 변환 후 JSON array 클래스를 통해<br> API 결과의 items 부분만 추출

  - 추출된 결과는 NewsVO 객체에 매핑하여 List형태로 생성

  - 생성된 List를 HomeController로 반환 

  - **JSON in java 라이브러리 사용**(pom.xml)

    ```xml
    <dependency>
    	<groupId>org.json</groupId>
    	<artifactId>json</artifactId>
    	<version>20160810</version>
    </dependency>
    ```

<br>

### :pushpin: 검색 API 처리 프로세스

![검색 API 프로세스](https://user-images.githubusercontent.com/55940552/141687947-3fb1fcc0-25b7-44a7-86e6-b604513cb88b.png) 

<br> 

### :pushpin: 2021-11-16일 목표

- 리스팅된 VO 객체를 View 단에 출력
- 부트스트랩의 카드 형태로 객체 리스팅
- 채팅방 기능 구현
  - 채팅방 프론트 엔드 구현