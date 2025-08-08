import { Component } from '@angular/core';

// Ng-Zorro
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzToolTipModule } from 'ng-zorro-antd/tooltip';

@Component({
  selector: 'app-sidebar',
  imports: [NzButtonModule, NzIconModule, NzMenuModule, NzToolTipModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {
  public isCollapsed = false;

  public toggleCollapsed(): void {
    this.isCollapsed = !this.isCollapsed;
  }
}
