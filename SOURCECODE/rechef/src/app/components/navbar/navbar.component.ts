import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  public loggedIn = false;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  public routerHome() {
    console.log("Navigating to home");
    this.router.navigateByUrl("home");
  }

  public routerEnter() {
    console.log("Navigating to enter");
    this.router.navigateByUrl("enter");
  }

  public routerLogin() {
    console.log("Navigating to login");
    this.router.navigateByUrl("login");
  }

  public routerRegister() {
    console.log("Navigating to register");
    this.router.navigateByUrl("register");
  }

  public routerLogout() {
    console.log("Logging out");
    localStorage.setItem("loggedin", "false");
    alert("You have been logged out.")
  }

  public routerFeatured() {
    console.log("Navigating to featured");
    this.router.navigateByUrl("featured");
  }

  public isLoggedIn() {
    return localStorage.getItem("loggedin") == "true";
  }
}
