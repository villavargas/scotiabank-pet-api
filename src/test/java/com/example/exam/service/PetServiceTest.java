package com.example.exam.service;

import com.example.exam.client.PetStoreClient;
import com.example.exam.model.AddPetRequest;
import com.example.exam.model.AddPetResponse;
import com.example.exam.model.Pet;
import com.example.exam.model.PetResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetStoreClient petStoreClient;

    @InjectMocks
    private PetService petService;

    @Test
    void getPet_cuandoExiste_retornaResponse() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Firulais");
        pet.setStatus("available");
        when(petStoreClient.getPetById(1L)).thenReturn(pet);

        PetResponse response = petService.getPet(1L);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("Firulais");
        assertThat(response.getStatus()).isEqualTo("available");
    }

    @Test
    void getPet_cuandoNoExiste_propagaExcepcion() {
        when(petStoreClient.getPetById(999L))
                .thenThrow(HttpClientErrorException.NotFound.class);

        assertThatThrownBy(() -> petService.getPet(999L))
                .isInstanceOf(HttpClientErrorException.NotFound.class);
    }

    @Test
    void addPet_retornaResponseConUuidYFechaActual() {
        AddPetRequest request = new AddPetRequest();
        request.setId(1L);
        request.setName("Firulais");
        request.setStatus("available");

        Pet createdPet = new Pet();
        createdPet.setId(1L);
        createdPet.setName("Firulais");
        createdPet.setStatus("available");
        createdPet.setPhotoUrls(List.of());
        when(petStoreClient.addPet(any())).thenReturn(createdPet);

        LocalDateTime before = LocalDateTime.now();
        AddPetResponse response = petService.addPet(request);
        LocalDateTime after = LocalDateTime.now();

        assertThat(response.getTransactionId()).isNotBlank();
        assertThat(response.getDateCreated()).isBetween(before, after);
        assertThat(response.isStatus()).isTrue();
        assertThat(response.getName()).isEqualTo("Firulais");
    }
}
