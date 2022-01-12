CSCI-3308-Fall21-013-05:

Team Name: Fruit Leather

Team Members: 
Ian Mckibben,
Daunte Mascotti,
Carson Syberg,
Rachel McMinn,
Md Mahmud,
Ferin Von Reich

Project Description:
Our web app will take user-inputted
ingredients and return a list of recipes
that you can cook with the given
ingredients. We will web scrape recipes
to find the necessary ingredients and will
compare them against the given list.
This will save time by giving the recipe
directly to the user instead of them
having to search the internet manually
given their ingredients. Furthermore, it
will allow them to have a wider variety of
selection, as they will be recommended
recipes that they have never made or
heard of before.

---
Video Demo
---
https://youtu.be/R5KJ9Ujlxzs

---
Deployment Link
---
https://rechef-f4067.web.app

---
Installation Instructions to Run Locally
--- 
Angular:
* Install npm, and from there install angular CLI
* Download the project from github using github clone
* cd to the SOURCECODE/rechef directory
* use "ng serve" to run the project
* If there is an error, use the following in order: "npm update", "ng update", "ng serve", and it should run fine after

MySQL:
* Install MySQL CLI 8.0
* Initalize the database and connect it with some password (remember this)
* Run the database creation script in the SOURCECODE/webscraping folder

Spring Boot:
* Install IntelliJIDE
* Change the password in application.properties of the SOURCECODE/springboot/rechef project to the password of your MySQL database
* Run the rechef application in SOURCECODE/springboot/rechef

Navigate to localhost:4200 and the application should be running

Webscraping with springboot:
* The webscraping gets called as part of the springboot app however there are a number of dependencies that will need to be installed in order to run it, and we have not found a quick way to do so.
* Since this is not needed to judge the rest of the functionality we do not reccomend trying to set this up locally, however if you want to follow these steps
* go to /SOURCECODE/springboot/scrapyIntegration/scraping/simply_scraper and modify the json_to_sql file to hold your mysql details
* Then install the following dependencies using pip install _ :
* requests, w3lib, html-text, jstyleson, scrapy, scrapy-deltafetch, extruct,  mysql-connector
* then test json_to_sql.py by running it in command line (will prompt for needed modules if incomplete)
* then go into ./spiders and test __init__.py by running it in command line (will prompt for needed modules if incomplete)
* Then change the directories given to the process builders in SOURCECODE/springboot/rechef/rechef/src/main/java/com/fruitleather/rechef/ScheduledTasks.java to reflect the correct paths to both the files refernces in this repo and your python executable (clearer when looking at the file, change any non-relative paths)
* Then the scrape and write to the db should work

---
Testing Instructions
---
In the angular project directory, run "ng test"

---
Directory Structure
---
* The directory structure for this repo is reasonably straightforward, and most folders have readme's that explain their purpose
* mysql holds the database creation script
* springboot holds the research and final springboot app that we used for backend bridging
* webscraping holds code related to the final webscraping implementation as well as the method we use for querying user inputted ingredients against our db
* wireframes holds wireframes
