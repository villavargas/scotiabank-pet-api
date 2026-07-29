package com.example.exam.service;

import com.example.exam.client.PetStoreClient;
import com.example.exam.model.AddPetRequest;
import com.example.exam.model.AddPetResponse;
import com.example.exam.model.Pet;
import com.example.exam.model.PetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetService implements PetPort {

    private final PetStoreClient petStoreClient;

    public PetResponse getPet(Long petId) {
        Pet pet = petStoreClient.getPetById(petId);
        log.info("Pet retrieved from Petstore: {}", pet);
        return PetResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .status(pet.getStatus())
                .build();
    }

    public AddPetResponse addPet(AddPetRequest request) {
        Pet pet = new Pet();
        pet.setId(request.getId());
        pet.setName(request.getName());
        pet.setStatus(request.getStatus());
        pet.setPhotoUrls(List.of());

        Pet createdPet = petStoreClient.addPet(pet);
        log.info("Pet created in Petstore: {}", createdPet);

        return AddPetResponse.builder()
                .transactionId(UUID.randomUUID().toString())
                .dateCreated(LocalDateTime.now())
                .status(true)
                .name(request.getName())
                .build();
    }
}
