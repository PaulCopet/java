import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { StudentListComponent } from './components/student-list/student-list.component';
import { StudentFormComponent } from './components/student-form/student-form.component';
import { Student } from './models/student.model';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, StudentListComponent, StudentFormComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  selectedStudent: Student | null = null;

  onEditStudent(student: Student): void {
    this.selectedStudent = student;
  }

  onStudentSaved(): void {
    this.selectedStudent = null;
  }

  onCancel(): void {
    this.selectedStudent = null;
  }
}
