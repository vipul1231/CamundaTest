FROM openjdk
RUN useradd -u 1001 -U --create-home -s /bin/bash appuser
WORKDIR /home/appuser
USER appuser
ADD target/camunda-starter-sample-1.0-SNAPSHOT.jar camunda-starter-sample-1.0-SNAPSHOT.jar
RUN mkdir /home/appuser/config
RUN chmod -R 0777 /home/appuser/config
EXPOSE 8081
ENTRYPOINT ["java","-jar","camunda-starter-sample-1.0-SNAPSHOT.jar"]