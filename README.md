## Kafka Service
Reactive service consuming messages from kafka topic, process and produce it to new topic

## Technology stack
Java 14, Gradle, Spring Boot, Spring Data (r2dbc), PostgreSQL, ProjectReactor, ApacheKafka, JUnit, Docker

## Checking out and Building
In a nutshell (win):

````
# Start ZooKeeper
$ .\bin\windows\zookeeper-server-start .\config\zookeeper.properties

# In a separate terminal, start Kafka broker
$ .\bin\windows\kafka-server-start .\config\server.properties

# In a separate terminal, createTopics 
$ .\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --topic topic_1 --partitions 1 --replication-factor 1
$ .\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --topic topic_2 --partitions 1 --replication-factor 1
````

