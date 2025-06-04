Spring 프레임워크의 개발 편의성을 체감해보고, 계정 보안 기능과 부트스트랩을 이용해 조금 더 발전된 게시판 프로젝트

구현 기능
   1. 게시판 CRUD, 검색
   2. 글에 이미지 파일 여러 개 업로드 가능
   3. 멤버 기능
      1. 회원가입, 로그인, 로그아웃, 개인정보 수정, 회원탈퇴
      2. 로그인 시 글, 댓글 작성할 때 작성자를 로그인 정보로 자동 대체
      3. 계정마다 프로필 사진 등록 가능
      4. 로그인에 보안 기능 추가

사용한 개발 언어 및 라이브러리
   1. 언어 : JDK 11
   2. DB : MySQL 8.0.41
   3. Servlet Container : Apache Tomcat 9.0
   4. 개발 툴 : sts3
   6. 라이브러리 : https://mvnrepository.com/
      * Spring에선 jar를 직접 다운받지 않고 프로젝트의 pom.xml 파일에 있는 dependencies에 추가하여 사용한다. (Lombok 제외)
      1. Project Lombok : annotation을 통해 getter, setter, 생성자, toString 등 코드를 자동으로 생성해주는 라이브러리
         * Project Lombok 사이트(https://projectlombok.org/)에서 .jar 파일을 받는다.
         * 받은 .jar를 직접 실행하여 STS.exe에 설치한다.
         * 이후 프로젝트의 pom.xml의 dependencies에 lombok을 추가한다. (https://projectlombok.org/setup/maven)
      2. 로그 관련
         * Logback Classic Module 1.4.5
         * Logback Core Module 1.4.5
         * Log4Jdbc Log4j2 JDBC 4 1 1.16 : 스프링에서 DB 처리 작업 시 생성된 SQL문을 로그로 확인할 수 있게 하는 라이브러리
      3. DB와 연결
         * MySQL Connector/J 8.0.33
         * MyBatis 3.5.10
         * MyBatis Spring 2.0.6
         * HikariCP 6.3.0 : DB Connection Pool을 여러 개 관리할 수 있게 해주는 라이브러리
      4. JSON
         * Jackson Databind 2.13.0
         * Jackson Dataformat XML 2.13.0
      5. 파일 업로드
         * Apache Commons FileUpload 1.4
         * Apache Commons IO 2.11.0
         * Thumbnailator 0.4.14
         * Tika Core 2.4.1
         * Apache Tika Parser Modules 2.4.1
      6. 계정 보안
         * TBA

Spring 설정법
  WIP
