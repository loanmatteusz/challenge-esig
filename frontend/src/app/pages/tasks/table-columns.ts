export interface ItemData {
  number: number;
  title: string;
  description: string;
  status: string;
  priority: string;
  responsible: string;
  deadline: string;
}

export const taskColumns = [
  {
    title: 'Number',
    compare: (a: ItemData, b: ItemData) => Number(b.number) - Number(a.number),
    priority: false
  },
  {
    title: 'Title',
    compare: (a: ItemData, b: ItemData) => a.title.toLowerCase().localeCompare(b.title.toLowerCase()),
    priority: false
  },
  {
    title: 'Description',
    compare: (a: ItemData, b: ItemData) => a.description.localeCompare(b.description),
    priority: false
  },
  {
    title: 'Status',
    compare: (a: ItemData, b: ItemData) => a.status.localeCompare(b.status),
    priority: false
  },
  {
    title: 'Priority',
    compare: (a: ItemData, b: ItemData) => a.priority.localeCompare(b.priority),
    priority: false
  },
  {
    title: 'Responsible',
    compare: (a: ItemData, b: ItemData) => a.responsible.localeCompare(b.responsible),
    priority: false
  },
  {
    title: 'Deadline',
    compare: (a: ItemData, b: ItemData) => {
      const dateA = new Date(a.deadline);
      const dateB = new Date(b.deadline);
      return dateA.getTime() - dateB.getTime();
    },
    priority: false
  },
  {
    title: 'Actions'
  }
];
