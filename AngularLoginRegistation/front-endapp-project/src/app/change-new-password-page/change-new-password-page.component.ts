import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient,HttpParams} from '@angular/common/http';
import { Router } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-change-new-password-page',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './change-new-password-page.component.html',
  styleUrl: './change-new-password-page.component.css'
})
export class ChangeNewPasswordPageComponent {

  newPassword: string="";
  email: string = "";

  constructor(private http:HttpClient , private router:Router,private route: ActivatedRoute)
  {

  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.email = params['email'] || "";
    });
  }

  ChangePassword(): void {
    const body = {
      email: this.email,
      newPassword: this.newPassword
    };

    this.http.put("http://localhost:8090/set-password", body, { responseType: "text" })
      .subscribe(
        (resultData: any) => {
          console.log(resultData);
          alert("Password Changed Successfully");
          this.router.navigate(['/']); // navigate to a different page if needed
        },
        (error) => {
          console.error("Error changing password", error);
          alert("Failed to change password. Please try again.");
        }
      );
  }

  // ChangePassword() :any {

  //   const headers = new HttpHeaders().set('newPassword',this.newPassword);

  //   return this.http.put("http://localhost:8090/set-password?email=truongdailoc.dev@gmail.com", headers, { responseType: "text"}).subscribe((resultData: any) => 
  //     {
  //       console.log(resultData);
  //       alert("Password Changed Successfully");
  //   });
  // }
}
