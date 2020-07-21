# INSTRUCTIONS

mvn clean  install

docker build -t cvc/cvcconsumer . 

docker run  -p 8084:8084 -t cvc/cvcconsumer:latest


In case of container  fail , run  :

mvn spring-boot:run
