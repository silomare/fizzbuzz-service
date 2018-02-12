# fizzbuzz-service (build/run instructions for windows)

1) clone repo


2) build:

mvn clean package site

2.1) check reports:

open target/site/project-reports.html


3) start service:

cd target

java -jar fizzbuzz-service-0.0.1-SNAPSHOT.jar --sink=kafkaSink
 

4) monitor kafka topic

4.1- start zookeper

.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

4.2 - start kafka server

.\bin\windows\kafka-server-start.bat .\config\server.properties

4.3 - start console listener on "fizzbuzz-result"

 .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic fizzbuzz-result

