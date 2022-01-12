import re
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

# The main class that allows us to scrape allrecipes.com, and parse the html metadata into a compact json file
# Scrapy automatically crawls through the html to find any links in the html, so all we need to do is feed the url to the saveRecipe function which parses and saves recipes to file
# The webscraper ignore any urls that lead offsite, such as any affiliate links and only scrapes recipes from the allrecipes.com domain
class allRecipesCrawler(CrawlSpider):
    name = 'allrecipes'
    allowed_domains = ['www.allrecipes.com']
    start_urls = ['https://www.allrecipes.com/']
    rules = (
        Rule(
            LinkExtractor(
                deny=[
                    re.escape('https://www.allrecipes.com/offsite'),
                    re.escape('https://www.allrecipes.com/whitelist-offsite'),
                ],
            ),
            process_links=queryCleaner,
            callback='saveRecipe',
            follow=True
        ),
    )


    def saveRecipe(self, response):
        if 'https://www.allrecipes.com/recipe/' in response.url:
            metadata = extruct.extract(
                response.text,
                response.url,
                syntaxes=['json-ld']
            )
            name = metadata['json-ld'][1]['name']
            rating = str(round(metadata['json-ld'][1]['aggregateRating']['ratingValue'], 1))
            imageUrl = metadata['json-ld'][1]['image']['url']
            cuisineType = metadata['json-ld'][1]['recipeCuisine'] # i.e. mexican, italian, etc..
            ingredients = metadata['json-ld'][1]['recipeIngredient'] # list
            instructions = metadata['json-ld'][1]['recipeInstructions'] # list, separated into multiple steps
            servings = re.search('^[^\d]*(\d+)', metadata['json-ld'][1]['recipeYield']).group() # uses regex to find the first digit value of a serving, used to keep it in with other scraped recipe's servings var
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
# The arguments provided saves the scrapy logfile to allrecipes.log and saves the parse recipes to allrecipes.json
command = 'scrapy crawl allrecipes --logfile allrecipes.log -o allrecipes.json -t jsonlines'
process = subprocess.run(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True)
