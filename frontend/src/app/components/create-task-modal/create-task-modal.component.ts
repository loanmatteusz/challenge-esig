import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

// Ng-Zorro
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzSelectModule } from 'ng-zorro-antd/select';

// Services
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { UserService } from '../../core/services/user/user.service';
import { TaskService } from '../../core/services/task/task.service';

// Entities
import { User } from '../../core/interfaces/user.interface';

@Component({
  selector: 'app-create-task-modal',
  imports: [
    ReactiveFormsModule,
    NzModalModule,
    NzFormModule,
    NzInputModule,
    NzSelectModule,
  ],
  templateUrl: './create-task-modal.component.html',
  styleUrl: './create-task-modal.component.scss'
})
export class CreateTaskModalComponent {
  @Output() refreshTaskList = new EventEmitter<void>();

  public users: User[] = [];

  public createForm!: FormGroup;

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

  public open() {
    this.isVisible = true;

    this.createForm = this.fb.group({
      title: ["", [Validators.required]],
      description: [""],
      status: ["", [Validators.required]],
      priority: ["", [Validators.required]],
      responsibleId: ["", [Validators.required]],
      deadline: [""],
    });
  }

  public confirmCreate() {
    if (this.createForm.invalid) {
      this.createForm.markAllAsTouched();
      return;
    }

    this.isLoading = true;
    const task = { ...this.createForm.value };

    this.taskService.createTask(task).subscribe({
      next: () => {
        this.notificationService.success('Success', 'Task created successfully!');
        this.isLoading = false;
        this.isVisible = false;
        this.refreshTaskList.emit();
      },
      error: (err) => {
        this.notificationService.error('Error', 'Failed to created task.');
        console.error(err);
        this.isLoading = false;
      }
    });
  }

  public handleCancel() {
    this.isVisible = false;
  }
}
