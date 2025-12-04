package com.example.backend.service;

import com.example.backend.dto.StudentDTO;
import com.example.backend.entity.Student;
import com.example.backend.mapper.StudentMapper;
import com.example.backend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/* Servicio que contiene la lógica de negocio para la gestión de estudiantes. */
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    /* Agrega un nuevo estudiante. */
    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDTO(savedStudent);
    }

    /* Lista todos los estudiantes. */
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return studentMapper.toDTOList(students);
    }

    /* Elimina un estudiante por su ID. */
    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /* Actualiza un estudiante existente. */
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            // Actualizamos los campos
            existingStudent.setName(studentDTO.getName());
            existingStudent.setEmail(studentDTO.getEmail());
            existingStudent.setAge(studentDTO.getAge());
            
            Student updatedStudent = studentRepository.save(existingStudent);
            return studentMapper.toDTO(updatedStudent);
        }
        return null;
    }
}
