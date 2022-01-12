import re
from scrapy.exceptions import CloseSpider
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from w3lib.url import url_query_cleaner
import extruct
import subprocess

# Cleans url queries to make each link more readable to the scraper
def queryCleaner(links):
    for link in links:
        link.url = url_query_cleaner(link.url)
        yield link

# The default variable that control how many recipes are scraped per run
recipeCount = 0

# The main class that allows us to scrape simplyrecipes.com, and parse the html metadata into a compact json file
# Scrapy automatically crawls through the html to find any links in the html, so all we need to do is feed the url to the saveRecipe function which parses and saves recipes to file
# The webscraper ignore any urls that lead offsite, such as any affiliate links and only scrapes recipes from the simplyrecipes.com domain
class simplyCrawler(CrawlSpider):
    name = 'simplyrecipes'
    allowed_domains = ['www.simplyrecipes.com']
    start_urls = ['https://www.simplyrecipes.com/']
    rules = (
        Rule(
            LinkExtractor(
                deny=[
                    re.escape('https://www.simplyrecipes.com/offsite'),
                    re.escape('https://www.simplyrecipes.com/whitelist-offsite'),
                ],
            ),
            process_links=queryCleaner,
            callback='saveRecipe',
            follow=True
        ),
    )

    def saveRecipe(self, response):
        global recipeCount
        if recipeCount == 10:  # change this condition to set how many recipes you want to scrape
            raise CloseSpider(f'Scraped {recipeCount} recipes')
        if 'https://www.simplyrecipes.com/recipes/' in response.url:
            metadata = extruct.extract(
                response.text,
                response.url,
                syntaxes=['json-ld']
            )
            name = metadata['json-ld'][0]['headline']
            rating = metadata['json-ld'][0]['aggregateRating']['ratingValue']
            imageUrl = metadata['json-ld'][0]['image'][0]['url']
            cuisineType = metadata['json-ld'][0]['recipeCuisine'][0]  # i.e. mexican, italian, etc..
            ingredients = metadata['json-ld'][0]['recipeIngredient']  # list
            instructions = metadata['json-ld'][0]['recipeInstructions']  # list, separated into multiple steps
            servings = metadata['json-ld'][0]['recipeYield']
            recipeCount += 1
            return {
                'url': response.url,
                'name': name,
                'servings': servings,
                'rating': rating,
                'imageUrl': imageUrl,
                'type': cuisineType,
                'ingredients': ingredients,
                'instructions': instructions,
            }

# The following code allows this script to call a terminal command that runs scrapy 
# The arguments provided saves the scrapy logfile to simplyrecipes.log and saves the parse recipes to simplyrecipes.json
command = 'scrapy crawl simplyrecipes --logfile simplyrecipes.log -o simplyrecipes.json -t jsonlines'
process = subprocess.run(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True)

