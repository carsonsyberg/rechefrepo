import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/proto/recipe';
import { RequestService } from 'src/app/services/request.service';

@Component({
  selector: 'featured-recipes',
  templateUrl: './featured-recipes.component.html',
  styleUrls: ['./featured-recipes.component.css']
})
export class FeaturedRecipesComponent implements OnInit {
  public recipes: Recipe[] = [];
  constructor(private requestService: RequestService) { }

  ngOnInit(): void {
    this.getRecipes();
  }

  public getRecipes() {
    this.requestService.getAllRecipes().subscribe(
      (response: Recipe[]) => {
        this.recipes = this.topEight(response);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert(error.message);
      }
    );
  }

  public topEight(response) {
    
    response.sort(function(a, b){
      return a.rating > b.rating ? 1 : -1;
    });
    
    return response.slice(0, 8);;
  }

}