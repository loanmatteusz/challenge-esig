import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

// Services
import { AuthService } from '../../../core/services/auth/auth.service';

import { AUTH_IMPORTS } from '../auth.imports';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-register',
  imports: [
    AUTH_IMPORTS,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  public registerForm: FormGroup;
  public isLoading: boolean = false;

  constructor(
    private router: Router,
    private authService: AuthService,
    private fb: FormBuilder,
    private notificationService: NzNotificationService,
  ) {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });
  }

  public onSubmit(): void {
    if (this.registerForm.valid) {
      this.isLoading = true;
      const { username, email, password, confirmPassword } = this.registerForm.value;
      if (password !== confirmPassword) {
        this.notificationService.warning("PASSWORD MISMATCH", "Passwords aren't equals!");
        return;
      }
      this.authService.register({ username, email, password }).subscribe({
        next: (_) => {
          this.notificationService.success("SUCCESS", "User created successfuly!");
          this.router.navigate(["/login"]);
          this.registerForm.reset();
          this.isLoading = false;
        },
        error: (err) => {
          this.notificationService.error("USER ALREADY EXISTS", err.error.message);
          console.error({ err });
          this.isLoading = false;
        }
      });
    }
  }
}
