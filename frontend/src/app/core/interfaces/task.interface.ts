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
