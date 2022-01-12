import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from '../../proto/user';
import { RequestService } from '../../services/request.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private requestService: RequestService, private router: Router) { }

  ngOnInit(): void {
  }

  public register() {
    let username: string = (document.getElementById("username") as HTMLInputElement).value;
    let password: string = (document.getElementById("password") as HTMLInputElement).value;
    let email: string = (document.getElementById("email") as HTMLInputElement).value;

    let userCheck : User = new User(username, password, email);

    // Send request to server
    console.log("Sending post request using:", username, password, email);

    let returnedUser = this.checkRegister(userCheck);

    // If exists, then login

    // TODO: complete function :D
  }

  public checkRegister(user: User) {
    this.requestService.registerUser(user).subscribe(
      (response: User) => {
        alert("User successfully registered");
        console.log(response);
        localStorage.setItem("loggedin", "true");
        this.router.navigateByUrl('');
        return response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
