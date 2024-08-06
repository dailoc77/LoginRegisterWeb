import { HttpClient } from '@angular/common/http';
import { Component, Injectable } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})

@Injectable({
  providedIn: 'root'
})

export class RegisterComponent {

  username: string="";
  email: string="";
  password: string="";

  showPassword: boolean = false;

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  constructor(private http:HttpClient , private router:Router)
  {

  }

  save()
  {
    let bodyData = {
      "username": this.username,
      "email": this.email,
      "password": this.password
    };

    this.http.post("http://localhost:8090/user/save", bodyData, { responseType: "text"}).subscribe((resultData: any) => 
      {
        console.log(resultData);
        alert("User Registered Successfully");
      }
    );
  }
}
