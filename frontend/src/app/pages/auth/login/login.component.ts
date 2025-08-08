// Angular Core
import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

// Services
import { AuthService } from '../../../core/services/auth/auth.service';
import { NzNotificationService } from "ng-zorro-antd/notification";

// Provider
import { AUTH_IMPORTS } from '../auth.imports';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    AUTH_IMPORTS,
    RouterLink,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  public loginForm: FormGroup;

  constructor(
    private router: Router,
    private authService: AuthService,
    private fb: FormBuilder,
    private notificationService: NzNotificationService,
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  public onSubmit(): void {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe({
        next: (response) => {
          this.authService.setToken(response.token);
          this.notificationService.success("SUCCESS", "You are logged in!");
          this.router.navigate(['/tasks']);
          this.loginForm.reset();
        },
        error: (err) => {
          this.notificationService.error("FAILED", "Invalid credentials!");
          console.error("Error to try to login: ", err);
        }
      });
    }
  }

  public goToRegister() {
    this.router.navigate(['/register']);
  }
}
