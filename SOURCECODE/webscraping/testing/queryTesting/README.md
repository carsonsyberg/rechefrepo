# Recipe Query Testing
This Directory has the component we are using for the recipe querying. 

* queryingResearch.txt has the ideas i have been testing for the past week+ as well as some research results
* spell.py  has a rudimentary local spellchecker which uses food.txt as a dictionary to correct user input into a predicted food (you can use :python3 spell.py correction "word": to test how good it is in cmdline)
* the methaphoneTesting directory is what I have been using to test fuzzy matching use the metaphone algorithm
* queryExamples.txt has the current query planned implementation which is being done in springboot now.


# Current Implementation Plan
While we were going to use metaphone, because the input from webscraping has quantity as part of the string of ingredients, metaphone match is being thrown off. I tried a few ways to parse out the quantities but for now we will use regular expressions to match a list of words inputted from the user - after the list has been converted into foods using the spellchecker to account for input error. The websites we scrape from are reasonably error free so this should not be a huge issue, and if I have time I will work more on the parsing quantities. This is being integrated into springboot now.