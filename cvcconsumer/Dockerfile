FROM openjdk:8
ADD target/cvcconsumer-1.0.0-SNAPSHOT.jar  cvc.jar
EXPOSE 8084
ENTRYPOINT [ "sh", "-c", "java -jar cvc.jar" ]



