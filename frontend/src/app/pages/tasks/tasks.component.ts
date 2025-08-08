import { Component, OnInit, ViewChild } from '@angular/core';

// Ng-Zorro
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzDividerModule } from 'ng-zorro-antd/divider';

// Task Resources
import { Task, TaskFilters } from '../../core/interfaces/task.interface';
import { TaskService } from '../../core/services/task/task.service';

// User Resources
import { User } from '../../core/interfaces/user.interface';
import { UserService } from '../../core/services/user/user.service';

// Enums
import { TaskPriority, TaskStatus } from '../../core/enums/task';

// Components
import { SearchBarComponent } from "../../components/search-bar/search-bar.component";
import { DeleteTaskModalComponent } from '../../components/delete-task-modal/delete-task-modal.component';
import { UpdateTaskModalComponent } from "../../components/update-task-modal/update-task-modal.component";

type TableProps = {
  pageIndex: number;
  total: number;
  pageSize: number;
}

@Component({
  selector: 'app-tasks',
  imports: [
    NzDividerModule,
    NzTableModule,
    SearchBarComponent,
    UpdateTaskModalComponent,
    DeleteTaskModalComponent,
  ],
  templateUrl: './tasks.component.html',
  styleUrl: './tasks.component.scss'
})
export class TasksComponent implements OnInit {
  @ViewChild(UpdateTaskModalComponent) updateTaskModal!: UpdateTaskModalComponent;
  @ViewChild(DeleteTaskModalComponent) deleteTaskModal!: DeleteTaskModalComponent;

  public tasks: Task[] = [];
  public users: User[] = [];

  public lastFilters: TaskFilters = {};

  public tableProps: TableProps = {
    pageIndex: 1,
    total: 0,
    pageSize: 10,
  };

  constructor(
    private taskService: TaskService,
    private userService: UserService,
  ) { }

  public ngOnInit(): void {
    this.getUsers();
    this.getTasks();
  }

  public getTasks(filters?: TaskFilters) {
    this.lastFilters = { ...this.lastFilters, ...filters };

    if (this.lastFilters.page === undefined || this.lastFilters.page === null) {
      this.lastFilters.page = (this.tableProps.pageIndex || 1) - 1;
    }
    if (this.lastFilters.size === undefined || this.lastFilters.size === null) {
      this.lastFilters.size = this.tableProps.pageSize || 10;
    }

    this.taskService.getTasks(this.lastFilters).subscribe((response) => {
      this.tasks = response.content;
      this.tableProps.pageIndex = response.page + 1;
      this.tableProps.pageSize = response.size;
      this.tableProps.total = response.totalElements;
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

  public onFiltersChanged(filters: TaskFilters) {
    this.tableProps.pageIndex = 1;
    filters.page = 0;
    filters.size = this.tableProps.pageSize;

    this.getTasks(filters);
  }

  public onPageChange(page: number) {
    this.tableProps.pageIndex = page;
    this.getTasks({ ...this.lastFilters, page: page - 1, size: this.tableProps.pageSize });
  }

  public onUpdateClick(task: Task) {
    this.updateTaskModal.open(task);
  }

  public onDeleteClick(task: Task) {
    this.deleteTaskModal.open(task);
  }
}
