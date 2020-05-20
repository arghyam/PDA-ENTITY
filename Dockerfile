FROM gradle:4.10.2-jdk8-alpine

USER root

#ADD ./src src
#DD ./gradle/wrapper gradle/wrapper
#ADD ./build.gradle   build.gradle
#ADD ./gradlew gradlew
#ADD ./gradlew.bat    gradlew.bat
#ADD ./settings.gradle    settings.gradle
ADD ./templates templates

#RUN gradle clean build -x test

ADD build/libs/socion-entity-0.0.1-SNAPSHOT.jar build/libs/socion-entity-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar", "./build/libs/socion-entity-0.0.1-SNAPSHOT.jar","--basePath","/templates/", "--autoScan", "--server"]


