# ToHome-BE

- 로컬 h2 DB로 연결

application.yml 파일에서 주석처리 되어있는 아래 부분 주석 해제

    datasource:
    url: jdbc:h2:tcp://localhost/~/tobehomeserver
    username: sa
    password:
    driver-class-name: org.h2.Driver

mysql DB 연결부분 주석처리

    datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://${database-host}:3306/tobehome?createDatabaseIfNotExist=true
    username: tobehome
    password: ${database-password}

- application-secret.properties 파일

  jwt.secret={비밀번호}