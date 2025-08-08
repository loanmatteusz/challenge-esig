import { Component, OnInit } from '@angular/core';

// Ng-Zorro
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { NzTableModule } from 'ng-zorro-antd/table';

// Entities
import { User } from '../../core/interfaces/user.interface';

// Services
import { UserService } from '../../core/services/user/user.service';

@Component({
  selector: 'app-users',
  imports: [NzDividerModule, NzTableModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss'
})
export class UsersComponent implements OnInit {
  public users: User[] = [];

  constructor(
    private userService: UserService,
  ) { }

  public ngOnInit(): void {
    this.getUsers();
  }

  public getUsers() {
    this.userService.getUsers().subscribe((response) => {
      this.users = response;
    });
  }
}
