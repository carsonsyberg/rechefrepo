import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EnterIngredientsComponent } from './components/enter-ingredients/enter-ingredients.component';
import { FeaturedRecipesComponent } from './components/featured-recipes/featured-recipes.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'enter', component: EnterIngredientsComponent },
  { path: 'featured', component: FeaturedRecipesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
