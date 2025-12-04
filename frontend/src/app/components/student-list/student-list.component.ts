import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Student } from '../../models/student.model';
import { StudentService } from '../../services/student.service';

@Component({
    selector: 'app-student-list',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './student-list.component.html',
    styleUrl: './student-list.component.css'
})
export class StudentListComponent implements OnInit {
    students: Student[] = [];
    @Output() editStudent = new EventEmitter<Student>();

    constructor(private studentService: StudentService) { }

    ngOnInit(): void {
        this.loadStudents();
    }

    loadStudents(): void {
        this.studentService.getStudents().subscribe({
            next: (response) => {
                this.students = response.data;
            },
            error: (error) => {
                console.error('Error al cargar estudiantes:', error);
                alert('Error al cargar la lista de estudiantes');
            }
        });
    }

    onEdit(student: Student): void {
        this.editStudent.emit(student);
    }

    onDelete(id: number | undefined): void {
        if (!id) return;

        if (confirm('¿Estás seguro de eliminar este estudiante?')) {
            this.studentService.deleteStudent(id).subscribe({
                next: () => {
                    this.loadStudents();
                },
                error: (error) => {
                    console.error('Error al eliminar estudiante:', error);
                    alert('Error al eliminar el estudiante');
                }
            });
        }
    }

    refreshStudents(): void {
        this.loadStudents();
    }
}
