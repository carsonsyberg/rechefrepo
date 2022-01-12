from bs4 import BeautifulSoup
import requests
url='https://www.delish.com/cooking/recipe-ideas/recipes/a49382/asian-chicken-noodle-recipe/'
req=requests.get(url)
content=req.text
soup=BeautifulSoup(content, 'html.parser')
ingredientsList=soup.find_all("div",class_="ingredient-lists")
directionsList=soup.find_all("div" ,class_="direction-lists")
imageList=soup.find("div" ,class_="recipe-body-content")
nameList=soup.find("div" ,class_="content-header-inner")
directions = ""
for ol in directionsList:
    for li in ol.find_all('li'):
        directions += li.text.replace("&nbsp", "")

for p in ingredientsList:
    ingredients=p.get_text().strip().replace("\t", "").replace("\n", " ")

img_link = imageList.find('source').get('data-srcset')
name = nameList.h1.text

lines = [name,"\n", "\nIngredients:\n\n", ingredients.replace("   ", "\n").replace("  ", " ").replace("\n ", " "), "\n", "\nDirections:\n\n", directions, "\nImage:\n\n", img_link]
name += ".txt"
with open(name, 'w') as f:
    for line in lines:
        f.write(line)
