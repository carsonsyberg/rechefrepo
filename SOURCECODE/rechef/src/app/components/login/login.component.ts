import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../proto/user';
import { RequestService } from '../../services/request.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private requestService: RequestService, private router: Router) { }

  public user: User = null;

  ngOnInit(): void {
  }

  public login() {
    let username: string = (document.getElementById("username") as HTMLInputElement).value;
    let password: string = (document.getElementById("password") as HTMLInputElement).value;

    let userCheck : User = new User(username, password, "");

    // Send request to server
    console.log("Sending post request using:", userCheck);

    this.requestService.loginUser(userCheck).subscribe(
      (response: User) => {
        // Get response
        this.user = response;

        // Login functionality
        if (this.user == null) {
          alert("User not found. Please try again");
          return;
        }

        // Log them into application
        alert("Logging in user: " + this.user.username);
        localStorage.setItem("loggedin", "true");
        this.router.navigateByUrl('');
        console.log(this.user);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert(error.message);
      }
    );

    // If exists, then login

    console.log(this.user);

    // TODO: complete function :D
  }

  public checkLogin(user: User) {
    this.requestService.loginUser(user).subscribe(
      (response: User) => {
        return response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
