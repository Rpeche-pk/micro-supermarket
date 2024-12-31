FROM amazoncorretto:17.0.9
LABEL author="Luis R. Peche"
LABEL maintainer="pecheaparcana1998@gmail.com"

COPY /target/maivibot-app.jar /app/maivibot-app.jar

WORKDIR /app
CMD ["java","-Duser.timezone=America/Lima", "-jar", "maivibot-app.jar"]