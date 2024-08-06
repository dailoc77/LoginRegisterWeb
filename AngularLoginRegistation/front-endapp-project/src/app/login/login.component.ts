import { HttpClient } from '@angular/common/http';
import { Component, Injectable } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

@Injectable({
  providedIn: 'root'
})

export class LoginComponent {

    username: string=""
    password: string=""
  
    constructor(private router: Router,private http:HttpClient)
    {
  
    }

    Login()
    {
      console.log(this.username)
      console.log(this.password)


      let bodyData = 
      {
        username : this.username,
        password : this.password
      }

      this.http.post("http://localhost:8090/user/login", bodyData).subscribe((resultData: any) => {
        console.log(resultData);

        if (resultData.message == "User Not Found") {
          alert("User does not exist");
        } else if (resultData.message == "Login Successful") {
          this.router.navigateByUrl('/home');
        } else {
          alert("Incorrect Username or Password");
        }
      });
    }
}
