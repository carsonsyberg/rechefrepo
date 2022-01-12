import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Recipe } from '../../proto/recipe'
import { IngredientList } from 'src/app/proto/ingredientList';
import { RequestService } from '../../services/request.service';

@Component({
  selector: 'app-enter-ingredients',
  templateUrl: './enter-ingredients.component.html',
  styleUrls: ['./enter-ingredients.component.css']
})
export class EnterIngredientsComponent implements OnInit {
  public recipes: Recipe[];
  public noneFound: boolean = false;

  constructor(private requestService: RequestService) { }

  ngOnInit(): void {
  }

  public enterIngredient() {
    let text: string = (document.getElementById("ingredients") as HTMLInputElement).value.trim().toLowerCase();
    var ingredients = text.split(/\n+/);
    ingredients.forEach((elem, i, o) => {if (/^\s*$/.test(elem)) {o.splice(i, 1);}});
    for(var i = 0; i < ingredients.length; i++) {
      ingredients[i] = ingredients[i].trim();
    }
    console.log(ingredients);

    let passList: IngredientList = new IngredientList(ingredients);

    this.requestService.getAllRecipes().subscribe(
      (response: Recipe[]) => {
        this.recipes = response;
        this.parseResults(ingredients);
      },
      (error: HttpErrorResponse) => {
        //console.log(error.message);
        alert(error.message);
      }
    );
  }

  public parseResults(ingredients) {
    let ret: Recipe[] = [];
    this.recipes.forEach((e, i, o) => {
      //console.log(e);
      let ings: string[] = [];
      let temp: string = "";
      let inString: boolean = false;
      for(var i = 0; i < e.ingredients.length; i++) {
        let curr = e.ingredients[i];
        if (curr == '*' && !inString) {
          inString = true;
        } else if (curr == '*' && inString) {
          inString = false;
          ings.push(temp);
          temp = "";
        } else if (inString) {
          temp += curr;
        }
      }

      //console.log(ings);
      
      // Check to make sure each ing inside enter string
      let worked: boolean = false;
      let tempVar = e.ingredients;
      for(var i = ingredients.length - 1; i >= 0; i--) {
        let ingString = e.ingredients.replaceAll('*', '');
        console.log(ingredients);
        //console.log(ingredients[i], ingString);
        if (ingString.indexOf(ingredients[i]) != -1) {
          console.log(ings[i], ingString);
          worked = true;
          break;
        }
        // if (text.indexOf(ings[i]) == -1) {
        //   console.log(ings[i], text);
        //   worked = false;
        //   break;
        // }
      }
      if (worked)
        // Repalce instructions
        ret.push(e);
    });

    for(var i = 0; i < ret.length; i++) {
      ret[i].instructions = ret[i].instructions.split("::");
      ret[i].instructions.splice(ret[i].instructions.length - 1, 1);
    }

    for(var i = 0; i < ret.length; i++) {
      ret[i].ingredients = ret[i].ingredients.replaceAll('*', '');
      ret[i].ingredients = ret[i].ingredients.replaceAll('[', '');
      ret[i].ingredients = ret[i].ingredients.replaceAll(']', '');
      ret[i].ingredients = ret[i].ingredients.split("::");
      ret[i].ingredients.splice(ret[i].ingredients.length - 1, 1);
      ret[i].showSteps = false;
    }

    console.log(ret);

    this.recipes = ret;
    if (this.recipes.length == 0) {
      this.noneFound = true;
    } else {
      this.noneFound = false;
    }
  }
}
