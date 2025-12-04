package com.example.backend.repository;

import com.example.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/* Repositorio para acceder a los datos de los estudiantes. */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /* Proyección para listar estudiantes de forma optimizada (solo id y nombre). */
    interface StudentSummary {
        Long getId();
        String getName();
    }

    /* Ejemplo de uso de la proyección */
    List<StudentSummary> findAllProjectedBy();
}
