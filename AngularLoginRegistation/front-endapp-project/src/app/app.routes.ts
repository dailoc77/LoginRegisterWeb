import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ForgotPasswordPageComponent } from './forgot-password-page/forgot-password-page.component';
import {ChangeNewPasswordPageComponent} from './change-new-password-page/change-new-password-page.component';

export const routes: Routes = [
    {
        path: '',
        component: LoginComponent
    },
    {
        path: 'home',
        component: HomeComponent
    },
    {
        path: 'register',
        component: RegisterComponent
    },
    {
        path: 'forgot-password-page',
        component: ForgotPasswordPageComponent
    },
    {
        path: 'set-password-page',
        component: ChangeNewPasswordPageComponent
    }
];


