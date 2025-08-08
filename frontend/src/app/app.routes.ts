import { Routes } from '@angular/router';
import { PublicLayoutComponent } from './layouts/public-layout/public-layout.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { PrivateLayoutComponent } from './layouts/private-layout/private-layout.component';
import { TasksComponent } from './pages/tasks/tasks.component';
import { authGuard } from './core/guards/auth.guard';
import { redirectIfAuthenticatedGuard } from './core/guards/redirect-if-authenticated.guard';
import { UsersComponent } from './pages/users/users.component';

export const routes: Routes = [
  {
    path: '',
    component: PublicLayoutComponent,
    children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      {
        path: 'login', component: LoginComponent,
        canActivate: [redirectIfAuthenticatedGuard], data: { title: "Login" }
      },
      {
        path: 'register', component: RegisterComponent,
        canActivate: [redirectIfAuthenticatedGuard], data: { title: "Register" }
      }
    ]
  },
  {
    path: '',
    component: PrivateLayoutComponent,
    canActivateChild: [authGuard],
    children: [
      {
        path: 'tasks', component: TasksComponent, data: { title: "Tasks" },
      },
      {
        path: 'users', component: UsersComponent, data: { title: "Users" },
      },
    ]
  },
  { path: '**', redirectTo: '' }
];
