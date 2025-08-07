// Angular Core
import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

// Ng-Zorro
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzInputModule } from 'ng-zorro-antd/input';

// Services
import { AuthService } from '../../../core/services/auth.service';
import { AUTH_PROVIDERS } from '../auth.provider';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    AUTH_PROVIDERS,
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
          this.router.navigate(['/tasks']);
          this.loginForm.reset();
        },
        error: (err) => {
          console.error("Error to try to login: ", err);
        }
      });
    }
  }

  public goToRegister() {
    this.router.navigate(['/register']);
  }
}
