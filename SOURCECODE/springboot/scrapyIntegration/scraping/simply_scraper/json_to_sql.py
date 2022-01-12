import json
import mysql.connector
from mysql.connector import errorcode

# python file open stuff: https://pynative.com/python-parse-multiple-json-objects-from-file/
# python json stuff: https://www.w3schools.com/python/python_json.asp
# python mysql stuff: https://dev.mysql.com/doc/connector-python/en/connector-python-example-connecting.html

# 1. Read json file into list &  Parse json string into python dictionary y = json.loads(x)
# our json file has a bunch of jsons within it, need to split them up before we can use json.loads on them

# list to hold the recipes as dictionaries
recipeList = []
with open('./spiders/simplyrecipes.json') as jsonFile:
     #turns each json {} in the file into a dictionary and adds to the list
    for jsonObj in jsonFile:
        try:
            recipeDict = json.loads(jsonObj)
            recipeList.append(recipeDict)
        except Exception:
            pass

print("Printing each JSON Decoded Object")
for recipe in recipeList:
    try:
        print("Recipe Name: ", recipe["name"], "\nServing Size: ", recipe["servings"], "\nRating: ", recipe["rating"], "\nIngredients: ", recipe["ingredients"])
    except Exception:
        pass


# 3. Send SQL insert statement with the python dictionary pieces as the values
# Connect to database
config = {
    'user': 'root',
    'password': 'Some2014',
    'host': '127.0.0.1',
    'database': 'rechefdb',
    'raise_on_warnings': True
}

cnx = mysql.connector.connect(**config)
cursor = cnx.cursor()

# Send queries to database
for recipe in recipeList:
    try: 
        instruction_string = ''
        ingredients_string = '['
        for steps in recipe["instructions"]:
            instruction_string += steps["text"]
        for ingredient in recipe["ingredients"]:
            ingredients_string += ingredient + ', '
        ingredients_string += ']'
        add_recipe = "INSERT INTO recipe (name, url, servings, rating, imageUrl, type, instructions, ingredients) SELECT * from (Select %s, %s, %s, %s, %s, %s, %s, %s) as temp where not exists (Select url from recipe where url=%s) LIMIT 1;"
        data_employee = (recipe["name"], recipe["url"], recipe["servings"], recipe["rating"], recipe["imageUrl"], recipe["type"], instruction_string, ingredients_string,recipe["url"])
        cursor.execute(add_recipe, data_employee)
    except Exception:
        pass

# must do this for it to go into the DB
cnx.commit()
cursor.close()
cnx.close()

# keep going from exception
# https://stackoverflow.com/questions/574730/python-how-to-ignore-an-exception-and-proceed

# add_recipe = "INSERT IGNORE INTO recipe (name, url, servings, rating, imageUrl, type, instructions, ingredients, hashURL) SELECT %s, %s, %s, %s, %s, %s, %s, %s, SHA2(%s,224);" --hash option
#  data_employee = (recipe["name"], recipe["url"], recipe["servings"], recipe["rating"], recipe["imageUrl"], recipe["type"], instruction_string, ingredients_string, recipe["url"])  --hash option

# https://www.geeksforgeeks.org/python-mysql-insert-record-if-not-exists-in-table/

# add without copies
# add_recipe = "INSERT INTO recipe (name, url, servings, rating, imageUrl, type, instructions, ingredients) SELECT * from (Select %s, %s, %s, %s, %s, %s, %s, %s) as temp where not exists (Select url from recipe where url=%s) LIMIT 1;"
# data_employee = (recipe["name"], recipe["url"], recipe["servings"], recipe["rating"], recipe["imageUrl"], recipe["type"], instruction_string, ingredients_string,recipe["url"])
