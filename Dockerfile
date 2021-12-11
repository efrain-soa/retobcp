
FROM maven:3-jdk-8-alpine as CONSTRUCTOR 
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
RUN mvn -B dependency:resolve dependency:resolve-plugins
COPY src /build/src
RUN mvn clean package

FROM adoptopenjdk/openjdk8:alpine-slim as RUNTIME

MAINTAINER efrain alvarez  eff.94nov@gmail.com

EXPOSE 8080

COPY --from=CONSTRUCTOR /build/target/*.jar app.jar

#19. INSTALANDO 'NANO': 
RUN apk add -u nano

#19. INSTALANDO 'CURL': 
RUN apk add -u curl

#20. EJECUTAR 'JAR': 
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]

