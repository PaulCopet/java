package com.example.backend.mapper;

import com.example.backend.dto.StudentDTO;
import com.example.backend.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

/* Mapper para convertir entre la entidad Student y StudentDTO. */
@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO toDTO(Student student);

    Student toEntity(StudentDTO studentDTO);

    List<StudentDTO> toDTOList(List<Student> students);
}
