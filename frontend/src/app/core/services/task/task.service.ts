import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GetTasksResponse, Task } from '../../interfaces/task.interface';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiUrl = 'http://localhost:8080/tasks';

  constructor(private http: HttpClient) { }

  public getTasks(): Observable<GetTasksResponse> {
    return this.http.get<GetTasksResponse>(this.apiUrl);
  }
}
