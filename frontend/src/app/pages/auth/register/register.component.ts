import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzFormModule } from 'ng-zorro-antd/form';
import { AuthService } from '../../../core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [
    ReactiveFormsModule,
    NzCardModule,
    NzFormModule,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  public registerForm: FormGroup;

  constructor(
    private router: Router,
    private authService: AuthService,
    private fb: FormBuilder,
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
      const { username, email, password, confirmPassword } = this.registerForm.value;
      if (password !== confirmPassword) {
        console.warn('Senhas não conferem!');
        return;
      }
      console.log('Cadastro enviado:', { username, email, password });

      this.authService.register({ username, email, password }).subscribe({
        next: (response) => {
          console.log({ response });
          this.router.navigate(["/login"]);
        }
      });

      this.registerForm.reset();
    }
  }
}
