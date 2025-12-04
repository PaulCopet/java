import { Component, Input, Output, EventEmitter, OnChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Student } from '../../models/student.model';
import { StudentService } from '../../services/student.service';

@Component({
    selector: 'app-student-form',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './student-form.component.html',
    styleUrl: './student-form.component.css'
})
export class StudentFormComponent implements OnChanges {
    @Input() student: Student | null = null;
    @Output() studentSaved = new EventEmitter<void>();
    @Output() cancelled = new EventEmitter<void>();

    formData: Student = {
        name: '',
        email: '',
        age: 0
    };

    isEditMode = false;

    constructor(private studentService: StudentService) { }

    ngOnChanges(): void {
        if (this.student) {
            this.formData = { ...this.student };
            this.isEditMode = true;
        } else {
            this.resetForm();
        }
    }

    onSubmit(): void {
        if (!this.isValid()) {
            alert('Por favor completa todos los campos correctamente');
            return;
        }

        if (this.isEditMode && this.formData.id) {
            this.studentService.updateStudent(this.formData.id, this.formData).subscribe({
                next: () => {
                    this.studentSaved.emit();
                    this.resetForm();
                },
                error: (error) => {
                    console.error('Error al actualizar estudiante:', error);
                    alert('Error al actualizar el estudiante');
                }
            });
        } else {
            this.studentService.createStudent(this.formData).subscribe({
                next: () => {
                    this.studentSaved.emit();
                    this.resetForm();
                },
                error: (error) => {
                    console.error('Error al crear estudiante:', error);
                    alert('Error al crear el estudiante');
                }
            });
        }
    }

    onCancel(): void {
        this.resetForm();
        this.cancelled.emit();
    }

    resetForm(): void {
        this.formData = {
            name: '',
            email: '',
            age: 0
        };
        this.isEditMode = false;
    }

    isValid(): boolean {
        return this.formData.name.trim() !== '' &&
            this.formData.email.trim() !== '' &&
            this.formData.age > 0;
    }
}
