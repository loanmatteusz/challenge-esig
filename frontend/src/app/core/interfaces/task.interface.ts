export type Task = {
  id: number;
  title: string;
  description: string;
  status: string;
  priority: string;
  responsibleId: string;
  deadline: string;
}

export interface GetTasksResponse {
  content: Task[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
  last: boolean;
}

export interface TaskFilters {
  id?: number;
  query?: string;
  responsibleId?: string;
  status?: 'PENDING' | 'IN_PROGRESS' | 'COMPLETED';
  page?: number;
  size?: number;
}

export interface UpdateTaskResponse {
  id: number,
  title: string;
  description: string;
  responsibleId: string;
  status: string;
  priority: string;
  deadline: string;
  ownerId: string;
}
