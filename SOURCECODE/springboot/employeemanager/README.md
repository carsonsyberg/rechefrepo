#Employee Manager Backend#
I wrote a version of this project, but the original source has clearer code so i have added an annoted version of their code here
(from https://www.youtube.com/watch?v=Gx4iBLKLVHk)
 

 Requirements to install:
 install mysql using: 'https://dev.mysql.com/doc/mysql-getting-started/en/' 
 Then access your server using 'mysql -u root -p' (will ask for password). Then create the database like this: 'create database employeemanager;'

 install postman http-client for http requests: 'https://learning.postman.com/docs/getting-started/installation-and-updates/#installing-postman-on-linux'
 (could also use httpie)


 This app shows how we can connect springboot to mysql and some of the advantages of using springboot

 Run the app form the main directory, then you can use postman to send / receive employee data from the backend


original source text
-------------------------------------------------------
employeemanager
App to manage employees
-------------------------------------------------------
end original source texts; here are my annotations;


Start.spring.io: Spring initializr
    -web form which generates your project with pom.xml file (dependency and project info)
    -dependencies
        -Spring Web; emebed web server and other web requirements
        -Spring Data JPA; map class to database 
        -MySQL Driver; driver to connect to database

Employee.java
    -holds the archetype for an employee
    -entity for database

Application.properties
    -holds the mySQL info needed to connect to database
    -jpa options for querying from sql
    -data description language and dialect set

EmployeeRepo
    -create a way to access employees in datbase

EmployeeService
    -different methods to change employee data in database
    -the deleteEmployeeById() is a good case for why to use springboot, it created the method on its own so I did not have to muck about understand what deleting an employee means
    -findEmployee also autocreated, query method in spring

EmployeeResources
    -rest controller, big use for springboot, makes this very easy

API Design:
HttP Requests -> Controller -> Service -> mySQL

- Controller: parse requests and pass to service / bACK
    to request
- Service: do all logic and acces mySQL
- mysql: Holds the data