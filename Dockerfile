FROM eclipse-temurin:17.0.7_7-jre-alpine@sha256:7cbe01fd3d515407f1fda1e68068831aa6ae4b6930d76cdaa43736dc810bbd1b

WORKDIR /opt/app

RUN addgroup --system javauser && \
    adduser -S -s /usr/sbin/nologin -G javauser javauser

COPY ./target/workout-api-0.0.1-SNAPSHOT.jar app.jar

RUN chown -R javauser:javauser /opt/app
USER javauser

ENTRYPOINT ["java", "-jar", "app.jar"]