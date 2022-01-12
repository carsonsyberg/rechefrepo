import re
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from w3lib.url import url_query_cleaner
import extruct

def queryCleaner(links):
    for link in links:
        link.url = url_query_cleaner(link.url)
        yield link

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
            callback='getJson',
            follow=True
        ),
    )

    def getJson(self, response):
        if 'https://www.simplyrecipes.com/recipes/' in response.url:
            metadata = extruct.extract(
                response.text,
                response.url,
                syntaxes=['json-ld']
            )
            name = metadata['json-ld'][0]['headline']
            rating = metadata['json-ld'][0]['aggregateRating']['ratingValue']
            imageurl = metadata['json-ld'][0]['image'][0]['url']
            timeToCook = [re.findall('\d+', metadata['json-ld'][0]['prepTime'])[0], re.findall('\d+', metadata['json-ld'][0]['cookTime'])[0], re.findall('\d+', metadata['json-ld'][0]['totalTime'])[0]] # [prep, cook, total time]
            cuisineType = metadata['json-ld'][0]['recipeCuisine'][0] # i.e. mexican, italian, etc..
            ingredients = metadata['json-ld'][0]['recipeIngredient'] # list
            instructions = metadata['json-ld'][0]['recipeInstructions'] # list, separated into multiple steps
            servings = metadata['json-ld'][0]['recipeYield']
            return {
                'url': response.url,
                'name': name,
                'servings': servings,
                'rating': rating,
                'imageurl': imageurl,
                'time': timeToCook,
                'type': cuisineType,
                'ingredients': ingredients,
                'instructions': instructions,
            }

## run using scrapy crawl simplyrecipes --logfile simplyrecipes.log -o simplyrecipes.jl  jsonlines
