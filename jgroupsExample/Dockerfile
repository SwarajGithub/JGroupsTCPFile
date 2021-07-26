FROM openjdk:11

WORKDIR /opt

COPY JGroupsExample.jar JGroupsExample.jar

COPY file.txt file.txt

COPY protocol.xml protocol.xml

COPY clusterFolder clusterFolder

ENTRYPOINT ["java", "-jar"]
CMD ["/opt/JGroupsExample.jar"]




