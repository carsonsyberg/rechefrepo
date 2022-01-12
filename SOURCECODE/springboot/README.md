#Springboot Setup and Intro Materials#

To setup, install springboot cli, and maven using this page:
https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started.installing

Then to test your install, type 'spring run testSpringboot.groovy' into terminal. If you set it up right, your browser at 'http://localhost:8080/' should say "Hello World!".

Springboot helps you create the controller and backend logic that interact with your front end and your database. See: 'https://www.ibm.com/cloud/learn/java-spring-boot'

The hello_world directory has an inital very easy example to get familar with how springboot works.

The employeemanager directory has a demo backend app with shows springboot interacting with https requests and mysql too.

The employeemanager app has a demo frontend app to connect and pass data through to datbase, what we based our app on.

sqlInjection.txt has resources to understand sql injection attack and how springboot's jpa prevents them. These practices are implemented in our application.

scrapyIntegration has the intermediary project we used to integrate webscraping calls from springbooot
