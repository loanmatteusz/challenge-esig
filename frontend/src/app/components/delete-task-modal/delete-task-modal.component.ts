import { Component, EventEmitter, Output } from '@angular/core';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { Task } from '../../core/interfaces/task.interface';
import { TaskService } from '../../core/services/task/task.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-delete-task-modal',
  imports: [NzModalModule],
  templateUrl: './delete-task-modal.component.html',
  styleUrl: './delete-task-modal.component.scss'
})
export class DeleteTaskModalComponent {
  @Output() refreshTaskList = new EventEmitter<void>();

  public isVisible: boolean = false;
  public isLoading: boolean = false;
  public task: Task | null = null;

  constructor(
    private taskService: TaskService,
    private notificationService: NzNotificationService,
  ) { }

  public open(task: Task) {
    this.task = task;
    this.isVisible = true;
  }

  public confirmDelete(): void {
    this.isLoading = true;
    this.taskService.deleteTask(this.task!.id).subscribe({
      next: () => {
        this.notificationService.success('SUCCESS', 'Task deleted successfully!');
        this.isLoading = false;
        this.isVisible = false;
        this.refreshTaskList.emit();
      },
      error: (err) => {
        this.notificationService.error('FAILED', 'Task has not deleted!');
        this.isLoading = false;
        console.error({ err });
      }
    });
  }

  public handleCancel(): void {
    this.isVisible = false;
  }
}
