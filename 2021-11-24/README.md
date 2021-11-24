## **2021-11-24 진행 상황**

***

<br> 

### :pushpin: 진행 사항

- **Youtube Data API V3를 활용한 영상 키워드 검색 서비스 구현**
  - JSon 형태의 결과 값에서 필요한 자료를 추출
  - 추출된 자료를 VO 형태로 매핑
  - model 객체에 해당 VO를 삽입 후 iFrame을 통해 View 단에서 출력



<br> 

### :pushpin: 보완 사항

- **YouTubeSearchServiceImpl 클래스에 영상 키워드 검색 서비스 구현**
  - searchWithYouTube 메서드 보완
    - JSon parsing 부분에서 에러
    - parsing 이후 유효한 값 확인
  - searchTest 메서드 성공
    - Json 형태의 자료가 String 형으로 반환됨
    - Json parsing을 통해 필요한 값 추출 필요