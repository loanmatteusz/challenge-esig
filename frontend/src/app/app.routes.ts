import { Routes } from '@angular/router';
import { PublicLayoutComponent } from './layouts/public-layout/public-layout.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { PrivateLayoutComponent } from './layouts/private-layout/private-layout.component';
import { TasksComponent } from './pages/tasks/tasks.component';
import { authGuard } from './core/guards/auth.guard';
import { redirectIfAuthenticatedGuard } from './core/guards/redirect-if-authenticated.guard';

export const routes: Routes = [
  {
    path: '',
    component: PublicLayoutComponent,
    children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: LoginComponent, canActivate: [redirectIfAuthenticatedGuard] },
      { path: 'register', component: RegisterComponent, canActivate: [redirectIfAuthenticatedGuard] }
    ]
  },
  {
    path: '',
    component: PrivateLayoutComponent,
    canActivateChild: [authGuard],
    children: [
      { path: 'tasks', component: TasksComponent }
    ]
  },
  { path: '**', redirectTo: '' }
];
