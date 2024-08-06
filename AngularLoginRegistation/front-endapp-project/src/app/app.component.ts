import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { ForgotPasswordPageComponent } from './forgot-password-page/forgot-password-page.component';
import { ChangeNewPasswordPageComponent } from './change-new-password-page/change-new-password-page.component';
import { LoginComponent } from './login/login.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,RegisterComponent,ForgotPasswordPageComponent,ChangeNewPasswordPageComponent,LoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'front-endapp-project';
}
