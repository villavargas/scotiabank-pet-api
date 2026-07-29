package com.example.exam.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class AddPetResponse {
    private String transactionId;
    private LocalDateTime dateCreated;
    private boolean status;
    private String name;
}
