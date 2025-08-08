import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from "@angular/router";

// Ng-Zorro
import { NzBreadCrumbModule } from 'ng-zorro-antd/breadcrumb';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { AuthService } from '../../core/services/auth/auth.service';

@Component({
  selector: 'app-private-layout',
  imports: [
    RouterLink,
    RouterOutlet,
    NzBreadCrumbModule,
    NzMenuModule,
    NzLayoutModule,
    NzIconModule,
    NzDividerModule,
  ],
  templateUrl: './private-layout.component.html',
  styleUrl: './private-layout.component.scss'
})
export class PrivateLayoutComponent {
  protected readonly date = new Date();

  constructor(
    private authService: AuthService,
    private router: Router,
  ) { }

  public logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
