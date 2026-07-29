package com.example.exam.client;

import com.example.exam.config.PetstoreProperties;
import com.example.exam.model.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PetStoreClient {

    private final RestTemplate restTemplate;
    private final PetstoreProperties properties;

    public Pet getPetById(Long petId) {
        return restTemplate.getForObject(properties.getBaseUrl() + "/pet/" + petId, Pet.class);
    }

    public Pet addPet(Pet pet) {
        return restTemplate.postForObject(properties.getBaseUrl() + "/pet", pet, Pet.class);
    }
}
