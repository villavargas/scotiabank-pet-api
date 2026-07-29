package com.example.exam.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddPetRequest {
    @NotNull(message = "id es requerido")
    @Positive(message = "id debe ser un número positivo")
    private Long id;

    @NotBlank(message = "status es requerido")
    private String status;

    @NotBlank(message = "name es requerido")
    private String name;
}
