import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../proto/user';
import { Recipe } from '../proto/recipe';
import { IngredientList } from '../proto/ingredientList';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private httpClient: HttpClient) { }
  
  public loginUser(user: User): Observable<User> {
    return this.httpClient.post<User>(`${this.apiServerUrl}/user/loginUser`, user);
  }
  
  public registerUser(user: User): Observable<User> {
    return this.httpClient.post<User>(`${this.apiServerUrl}/user/registerUser`, user);
  }
  
  public checkExists(user: User): Observable<User> {
    return this.httpClient.post<User>(`${this.apiServerUrl}/user/checkExists`, user);
  }

  public getAllRecipes(): Observable<Recipe[]> {
    return this.httpClient.get<Recipe[]>(`${this.apiServerUrl}/user/getAllRecipes`);
  }
}
