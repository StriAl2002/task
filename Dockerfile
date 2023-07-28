FROM openjdk
EXPOSE 4001
COPY src/main/ /tmp
WORKDIR /tmp
RUN javac ServerApplication.java
CMD java com.teraplantask.server.ServerApplication
