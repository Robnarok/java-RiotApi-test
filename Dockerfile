FROM gradle:6.8.3-jdk11 as build
COPY ./yoneban ./
RUN gradle --no-daemon clean bootJar

FROM gradle:6.8.3-jre11
COPY --from=build /home/gradle/build/libs/*.jar app.jar
CMD java -jar app.jar


