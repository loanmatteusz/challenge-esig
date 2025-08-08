// src/app/pages/auth/auth-imports.ts
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

// NG-ZORRO
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCardModule } from 'ng-zorro-antd/card';

export const AUTH_IMPORTS = [
  ReactiveFormsModule,
  RouterModule,
  NzFormModule,
  NzInputModule,
  NzButtonModule,
  NzCardModule
];
