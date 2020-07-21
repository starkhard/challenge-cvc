
 # INSTRUCTIONS

mvn clean  install

docker build -t cvc/cvctest .

docker run  -p 8084:8084 -t cvc/cvctest:latest


In case of container  fail , run  :

mvn spring-boot:run


  <h1> SWAGGER </h1>
 http://localhost:8080/swagger-ui.html#
