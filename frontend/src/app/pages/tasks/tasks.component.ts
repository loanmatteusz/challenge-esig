import { Component, OnInit } from '@angular/core';

// Ng-Zorro
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzDividerModule } from 'ng-zorro-antd/divider';

// Task Resources
import { Task } from '../../core/interfaces/task.interface';
import { TaskService } from '../../core/services/task/task.service';
import { User } from '../../core/interfaces/user.interface';
import { UserService } from '../../core/services/user/user.service';

enum TaskStatus {
  PENDING = "Pendente",
  IN_PROGRESS = "Em progresso",
  COMPLETED = "Finalizada",
}

enum TaskPriority {
  LOW = "Baixa",
  MEDIUM = "Media",
  HIGH = "Alta",
}

@Component({
  selector: 'app-tasks',
  imports: [NzDividerModule, NzTableModule],
  templateUrl: './tasks.component.html',
  styleUrl: './tasks.component.scss'
})
export class TasksComponent implements OnInit {
  public tasks: Task[] = [];
  public users: User[] = [];

  constructor(
    private taskService: TaskService,
    private userService: UserService,
  ) { }

  public ngOnInit(): void {
    this.getUsers();
    this.getTasks();
  }

  public getTasks() {
    this.taskService.getTasks().subscribe((response) => {
      this.tasks = response.content;
    });
  }

  public getUsers() {
    this.userService.getUsers().subscribe((response) => {
      this.users = response;
    });
  }

  public formatStatus(statusKey: string): string {
    return TaskStatus[statusKey as keyof typeof TaskStatus];
  }

  public formatPriority(priorityKey: string): string {
    return TaskPriority[priorityKey as keyof typeof TaskPriority];
  }

  public getResponsibleNameById(responsibleId: string): string {
    return this.users.find(user => user.id === responsibleId)?.name!;
  }
}
