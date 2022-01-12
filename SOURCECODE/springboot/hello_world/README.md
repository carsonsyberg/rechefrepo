Quick Hello World project built using springboot from: https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started.first-application

-pom.xml is the maven file which specifies the dependencies needed for project.
--you can use 'mvn dependency:tree" to view the dependency tree

-the actual code is in src/main/java/MyApplication.java, there are coments dhould what happens

-in production you would use a java executable jar to deploy it with 'java -jar target/myproject-0.0.
1-SNAPSHOT.jar'

To run the example, tou can use 'mvn spring-boot:run' in this directory, and the text will appear in
'http://localhost:8080/' in your browswer.                                     