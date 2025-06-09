1. 개요
   * Spring 프레임워크의 개발 편의성을 체감
   * 부트스트랩을 이용해 조금 더 발전된 UI를 제공
   * 계정 보안 기능 추가

2. 구현 기능
   1) 게시판 CRUD, 검색
   2) 글에 이미지 파일 여러 개 업로드 가능
      * 파일 크기 제한, 확장자 제한(executables 불가능)
      * 이미지 파일은 게시판에 표시, 나머지 파일은 다운로드 가능
      * UUID 사용
      * 글 등록 후 업로드한 파일 삭제 가능
   3) 스케줄링
      * 매일 특정 시간에 DB와 실제 파일을 스캔해서 DB와의 연결이 끊긴 파일을 삭제
   4) 멤버 기능
      * 회원가입, 로그인, 로그아웃, 개인정보 수정, 회원탈퇴
      * 글, 댓글을 작성할 때 로그인한 상태라면 작성자 부분을 로그인 정보를 이용해 자동 대체
      * 계정마다 프로필 사진 등록 가능
      * 로그인에 보안 기능 추가

4. 사용한 개발 언어 및 라이브러리
   1) 언어 : JDK 11
   2) DB : MySQL 8.0.41
   3) Servlet Container : Apache Tomcat 9.0
   4) 개발 툴 : sts3
   5) 라이브러리 : https://mvnrepository.com/
      1) Project Lombok : annotation을 통해 getter, setter, 생성자, toString 등 반복되는 코드를 자동으로 생성해주는 라이브러리
      3) 로그 관련
          * Logback Classic Module 1.4.5
          * Logback Core Module 1.4.5
          * Log4Jdbc Log4j2 JDBC 4 1 1.16 : 스프링에서 DB 처리 작업 시 생성된 SQL문을 로그로 확인할 수 있게 하는 라이브러리
      4) DB와 연결
          * MySQL Connector/J 8.0.33
          * MyBatis 3.5.10
          * MyBatis Spring 2.0.6
          * HikariCP 6.3.0 : DB Connection Pool을 여러 개 관리할 수 있게 해주는 라이브러리
      5) JSON
          * Jackson Databind 2.13.0
          * Jackson Dataformat XML 2.13.0
      6) 파일 업로드
          * Apache Commons FileUpload 1.4
          * Apache Commons IO 2.11.0
          * Thumbnailator 0.4.14
          * Tika Core 2.4.1
          * Apache Tika Parser Modules 2.4.1
      7) 스케줄링
          * Quartz 2.3.2
          * Quartz Jobs 2.3.2
      8) 계정 보안
          * TBA

5. Spring 설정법 WIP
