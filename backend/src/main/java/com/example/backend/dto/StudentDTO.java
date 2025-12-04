package com.example.backend.dto;

import lombok.Data;

/* Objeto de Transferencia de Datos (DTO) para Estudiante.
   Se utiliza para exponer solo los datos necesarios a través de la API.*/
@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private Integer age;
}
