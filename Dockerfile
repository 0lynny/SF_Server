FROM amd64/amazoncorretto:17

WORKDIR /app

COPY ./build/libs/SFaaS-0.0.1-SNAPSHOT.jar /app/SFaaS.jar

CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=deploy", "SFaaS.jar"]