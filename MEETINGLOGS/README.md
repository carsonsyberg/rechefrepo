# Meeting Logs #

Meeting 1 (9/17/21):
- Discussed Recipe Website with TA, got the ok to continue
- Discussed using angular or possibly react for the front end
- Discussed using django, or springboot, or flask for the backend
- TA said ideas were acceptable, suggested ensuring our software stack was used in industry 

Meeting 2 (9/24/21):
- Discussed the general format of the meeting
- general progress update
- induvidual updates for the weekly deliverables
- statement of next weeks deliverables
- Asked questions that we had
- Discussed the research we would begin for next week (angular, sql, springboot)

Meeting 3 (10/1/21):
- Discussed our Project 2 Milestone
	* showed wireframes and discussed graphical ideas
	* showed ta our Jira board with questions about user story structures
- iduvidual updates (springboot and angular research)
- discussed deliverables for the next week
	- front end of login page
	- working connection from angular to springboot (maybe sql)
	- research of webscraping / how we will get recipes
		
Meeting 4:
- Ta was gone so we did not have the meeting

Meeting 5 (10/15/21):
- Application diagram not seen in meeting since ta was late, it is in the milestone
- Demo Features:
	* Demo 1: Website
		1. Working Register Page which can interface from angular to springboot to mysql, can take in information and add it to the database
		2. Working Login Page which checks if the user exists and redirects if true
		3. Enter Ingredients Page in which ingredients can be typed in, not interfacing with backend
	* Demo 2: Web Scraping for finding recipes
		1. Using Scrapy to return all recipes elements from a recipe from simplyrecipes.com
-  What Worked In the Demo:
	*  features developed so far
- What issues were faced during development/demo:
	* Mismatch of software versions between our computers caused problems if working together
		Especially java mismatches
	* Not matching different people's work together, for example two sql creation scripts ended up getting made
- What were the suggestions offered by the TA:
	* We should ensure we know how things will change in the gui if a user is signed in or . 
	* We should flesh out the logic for processing a user registration.
	* We should not have two apps, one for the front end service and one for webscraping that may be reading and writing to the same database at the same time. This could cause errors. To deal with this, we will use springboot to trigger the webscraping at certain times (application architecture updated to reflect this).
	* TA said we should look into checks to make sure that we do not scrape malicious code For example, scrape sql which could be used to mess up our database. Planning on using springboot JPA built in functionality to handle this.
	* Also should implement checks in webscraping to make sure we scrape recipes and not home pages or something else
	* We should determine the exact websites we are scraping and their url structure so we know what to scrape/crawl
	* Ta said we should look further into the database/queries we use to generate recipes from ingredients, we need to look at:
		1. Cases in which the ingredients may be misspelled, hyphenated, or otherwise different, in the original source
		2. Cases in which no matches arise but some options in the database are very close
		3. How to tie together recipes by ingredients when we scrape them to reduce query time


Meeting 6 (10/22/21):
- Discussed Overall Contributions
- Discussed Milestone 4

Meeting 7 (10/29/21):
- No Meeting, TA was out

Meeting 8 (11/5/21):
- Discussed Overall Contributions
	* Carson worked on the conversion from webscraping json to sql
	* Ferin talked about metaphone algorithm, pattern matching, and spellchecking as ways to handle recipe querying
	* Md and Daunte talked about finishing up the webscraper
	* Ian worked on fronted for ingredients and recipe page
- Asked question about deliverables for milestone 5 since they were unclear
	* Ta suggested emailing professor



