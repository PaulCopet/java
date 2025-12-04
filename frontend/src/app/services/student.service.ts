import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from '../models/student.model';
import { ApiResponse } from '../models/api-response.model';

@Injectable({
    providedIn: 'root'
})
export class StudentService {
    private apiUrl = 'http://localhost:8080/api/students';

    constructor(private http: HttpClient) { }

    getStudents(): Observable<ApiResponse<Student[]>> {
        return this.http.get<ApiResponse<Student[]>>(this.apiUrl);
    }

    createStudent(student: Student): Observable<ApiResponse<Student>> {
        return this.http.post<ApiResponse<Student>>(this.apiUrl, student);
    }

    updateStudent(id: number, student: Student): Observable<ApiResponse<Student>> {
        return this.http.put<ApiResponse<Student>>(`${this.apiUrl}/${id}`, student);
    }

    deleteStudent(id: number): Observable<ApiResponse<void>> {
        return this.http.delete<ApiResponse<void>>(`${this.apiUrl}/${id}`);
    }
}
