# sample-spring-boot-microservice
Demo example to create microservices using spring cloud components

auth-service -> Authentication service using JWT returns jwt auth token
config-server -> spring cloud config server component to have all the configuration files corresponding to each service at one place.
zuul-proxy -> API gateway
service-registery -> Spring cloud netflix eurka service registery
hystrix-dashboard -> Spring cloud hystrix circuit breaker dashboard
catlog-service and product-service -> spring boot microservice with circuit breaker, PACT framework and Junit-Mockito test cases.
kafka-sleuth -> spring boot service using spring cloud sleuth and spring kafka
pact -> contract between catlog and product service.
