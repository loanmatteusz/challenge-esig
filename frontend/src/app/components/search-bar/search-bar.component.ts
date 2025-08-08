import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { User } from '../../core/interfaces/user.interface';

@Component({
  selector: 'app-search-bar',
  imports: [
    ReactiveFormsModule,
    NzFormModule,
    NzInputModule,
    NzButtonModule,
    NzSelectModule,
  ],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.scss'
})
export class SearchBarComponent implements OnInit {
  @Input() public users: User[] = [];
  @Output() public filters = new EventEmitter<any>();

  public filterForm: FormGroup;

  public statusOptions = [
    { label: 'Pendente', value: 'PENDING' },
    { label: 'Em progresso', value: 'IN_PROGRESS' },
    { label: 'Finalizada', value: 'COMPLETED' },
  ];

  constructor(private fb: FormBuilder) {
    this.filterForm = this.fb.group({
      id: [null],
      query: [''],
      responsibleId: [null],
      status: [null]
    });
  }

  public ngOnInit() {
    console.log({ users: this.users });
  }


  public submitForm() {
    this.filters.emit(this.filterForm.value);
  }

  public resetForm() {
    this.filterForm.reset();
    this.filters.emit(this.filterForm.value);
  }
}
