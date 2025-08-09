import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GetTasksResponse, Task, TaskFilters, UpdateTaskResponse } from '../../interfaces/task.interface';
import { environment } from '../../../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiUrl = `${environment.apiUrl}/tasks`;

  constructor(private http: HttpClient) { }

  public createTask(task: Task): Observable<Task> {
    return this.http.post<Task>(this.apiUrl, task);
  }

  public getTasks(filters?: TaskFilters): Observable<GetTasksResponse> {
    let params = new HttpParams();
    if (filters) {
      Object.entries(filters).forEach(([key, value]) => {
        if (value !== undefined && value !== null) {
          params = params.set(key, value.toString());
        }
      });
    }
    return this.http.get<GetTasksResponse>(this.apiUrl, { params });
  }

  public updateTask(task: Task): Observable<UpdateTaskResponse> {
    return this.http.put<UpdateTaskResponse>(`${this.apiUrl}/${task.id}`, task);
  }

  public deleteTask(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
