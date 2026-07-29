package com.example.exam.model;

import lombok.Data;
import java.util.List;

@Data
public class Pet {
    private Long id;
    private String name;
    private String status;
    private List<String> photoUrls;
}
