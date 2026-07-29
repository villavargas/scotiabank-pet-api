package com.example.exam.constant;

public final class PetConstants {

    // === API Paths ===
    public static final String API_BASE_PATH = "/api";
    public static final String PET_PATH = "/pet";
    public static final String PET_BY_ID_PATH = "/pet/{petId}";

    // === OpenAPI — Tags ===
    public static final String TAG_NAME = "Pet";
    public static final String TAG_DESCRIPTION = "API para gestión de mascotas";

    // === OpenAPI — GET pet ===
    public static final String GET_PET_SUMMARY = "Obtener mascota por ID";
    public static final String GET_PET_DESCRIPTION = "Consulta la información de una mascota en Petstore";

    // === OpenAPI — POST pet ===
    public static final String ADD_PET_SUMMARY = "Crear mascota";
    public static final String ADD_PET_DESCRIPTION = "Registra una nueva mascota en Petstore";

    // === OpenAPI — Response descriptions ===
    public static final String DESC_PET_FOUND = "Mascota encontrada";
    public static final String DESC_PET_CREATED = "Mascota creada exitosamente";
    public static final String DESC_PET_NOT_FOUND = "Mascota no encontrada";
    public static final String DESC_INVALID_INPUT = "Datos de entrada inválidos";

    // === Trazabilidad ===
    public static final String CORRELATION_ID_HEADER = "X-Correlation-ID";
    public static final String MDC_CORRELATION_ID = "correlationId";

    private PetConstants() {
    }
}
