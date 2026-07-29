package com.example.exam.service;

import com.example.exam.model.AddPetRequest;
import com.example.exam.model.AddPetResponse;
import com.example.exam.model.PetResponse;

public interface PetPort {
    PetResponse getPet(Long petId);
    AddPetResponse addPet(AddPetRequest request);
}
