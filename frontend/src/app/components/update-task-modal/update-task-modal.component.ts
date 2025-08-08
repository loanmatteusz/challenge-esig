import { Component, EventEmitter, OnInit, Output } from '@angular/core';

// Ng-Zorro
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzInputModule } from 'ng-zorro-antd/input';

// Entities
import { Task } from '../../core/interfaces/task.interface';
import { User } from '../../core/interfaces/user.interface';

// Services
import { UserService } from '../../core/services/user/user.service';
import { TaskService } from '../../core/services/task/task.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-update-task-modal',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NzModalModule,
    NzFormModule,
    NzInputModule,
    NzSelectModule,
  ],
  templateUrl: './update-task-modal.component.html',
  styleUrls: ['./update-task-modal.component.scss'],
})
export class UpdateTaskModalComponent implements OnInit {
  @Output() refreshTaskList = new EventEmitter<void>();

  public users: User[] = [];

  public task: Task | null = null;
  public updateForm!: FormGroup;

  public statusOptions = [
    { label: 'Pendente', value: 'PENDING' },
    { label: 'Em progresso', value: 'IN_PROGRESS' },
    { label: 'Finalizada', value: 'COMPLETED' },
  ];

  public priorityOptions = [
    { label: 'Baixa', value: 'LOW' },
    { label: 'Media', value: 'MEDIUM' },
    { label: 'Alto', value: 'HIGH' },
  ];

  public isVisible = false;
  public isLoading = false;

  constructor(
    private userService: UserService,
    private taskService: TaskService,
    private notificationService: NzNotificationService,
    private fb: FormBuilder,
  ) { }

  public ngOnInit(): void {
    this.userService.getUsers().subscribe((users) => this.users = users);
  }

  public open(task: Task) {
    this.task = task;
    this.isVisible = true;

    this.updateForm = this.fb.group({
      title: [task.title, [Validators.required]],
      description: [task.description],
      status: [task.status, [Validators.required]],
      priority: [task.priority, [Validators.required]],
      responsibleId: [task.responsibleId, [Validators.required]],
      deadline: [task.deadline],
    });
  }

  public confirmUpdate() {
    if (this.updateForm.invalid) {
      this.updateForm.markAllAsTouched();
      return;
    }

    this.isLoading = true;
    const updatedTask = { ...this.task, ...this.updateForm.value };

    this.taskService.updateTask(updatedTask).subscribe({
      next: () => {
        this.notificationService.success('Success', 'Task updated successfully!');
        this.isLoading = false;
        this.isVisible = false;
        this.refreshTaskList.emit();
      },
      error: (err) => {
        this.notificationService.error('Error', 'Failed to update task.');
        console.error(err);
        this.isLoading = false;
      }
    });
  }

  public handleCancel() {
    this.isVisible = false;
  }
}
