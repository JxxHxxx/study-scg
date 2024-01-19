### yml 을 통한 gatewasy 설정 예시


```
spring:
  cloud:
    gateway:
      mvc:
        routes:
          - id: approval
            uri: http://localhost:8010
            predicates:
              - Header=destination, approval // HTTP 헤더에 destination key 값이 approval 일 경우 위 uri 로 라우팅

          - id: vacation
            uri: http://localhost:8080
            predicates:
              - Header=destination, vacations // HTTP 헤더에 destination key 값이 vacations 일 경우 위 uri 로 라우팅
```
