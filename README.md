## Spring Cloud Gateway
Spring Cloud Gatewasy 4 버전 이전에는 Reactive 환경에서 Gateway 를 구현해야 했다.    
4버전부터는 servlet, tomcat WAS 기반의 MVC 환경에서도 SCG 를 사용할 수 있게 되어 가볍게 살펴보고 코드를 작성해보았다. 

## yml 을 통한 gatewasy 설정 예시


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
