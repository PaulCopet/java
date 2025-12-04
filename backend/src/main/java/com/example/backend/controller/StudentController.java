package com.example.backend.controller;

import com.example.backend.dto.ApiResponse;
import com.example.backend.dto.StudentDTO;
import com.example.backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* Controlador REST para manejar las peticiones relacionadas con estudiantes. */
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /* Endpoint para obtener todos los estudiantes. */
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(new ApiResponse<>("Lista de estudiantes obtenida con éxito", students, HttpStatus.OK.value()));
    }

    /* Endpoint para crear un nuevo estudiante. */
    @PostMapping
    public ResponseEntity<ApiResponse<StudentDTO>> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.addStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Estudiante creado con éxito", createdStudent, HttpStatus.CREATED.value()));
    }

    /* Endpoint para eliminar un estudiante. */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>("Estudiante eliminado con éxito", null, HttpStatus.OK.value()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Estudiante no encontrado", null, HttpStatus.NOT_FOUND.value()));
        }
    }

    /* Endpoint para actualizar un estudiante. */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDTO>> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        if (updatedStudent != null) {
            return ResponseEntity.ok(new ApiResponse<>("Estudiante actualizado con éxito", updatedStudent, HttpStatus.OK.value()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Estudiante no encontrado", null, HttpStatus.NOT_FOUND.value()));
        }
    }
}
