package com.bfb.rental.interfaces.dtos.response;

import java.time.LocalDateTime;
import lombok.*;
/**
 * JUSTIFICATION : DTO envelope pour TOUTES les réponses API
 *
 * Donne une structure cohérente :
 * {
 *   "success": true,
 *   "message": "Opération réussie",
 *   "data": { ... },
 *   "timestamp": "2024-01-15T10:30:00Z"
 * }
 *
 * PATTERN : API Envelope Pattern / Response Wrapper Pattern
 * BENEFIT : Le client sait toujours où trouver les données
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    /**
     * Factory methods pour créer les réponses
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> success(T data) {
        return success("Opération réussie", data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
}