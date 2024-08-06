import { Component } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-forgot-password-page',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './forgot-password-page.component.html',
  styleUrl: './forgot-password-page.component.css'
})
export class ForgotPasswordPageComponent {

  email: string="";

  constructor(private http:HttpClient , private router:Router)
  {

  }

  // SendMessageToEmail() : any {
  //   const params = new HttpParams().set('email', this.email);

  //   // let body = {"email": this.email};
    
  //   return this.http.post("http://localhost:8090/forgot-password", params, { responseType: "text"}).subscribe((resultData: any) => 
  //     {
  //       console.log(resultData);
  //       alert("Email Sent Successfully");
  //   });
  // }

  SendMessageToEmail(): void {
    const params = new HttpParams().set('email', this.email);

    this.http.post("http://localhost:8090/forgot-password", null, { params: params, responseType: "text" })
      .subscribe(
        (resultData: any) => {
          console.log(resultData);
          alert("Email Sent Successfully");
          this.router.navigate(['/change-new-password'], { queryParams: { email: this.email } });
        },
        (error) => {
          console.error("Error sending email", error);
          alert("Failed to send email. Please try again.");
        }
      );
  }
}

