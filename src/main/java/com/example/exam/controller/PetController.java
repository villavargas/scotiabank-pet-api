package com.example.exam.controller;

import com.example.exam.constant.PetConstants;
import com.example.exam.model.AddPetRequest;
import com.example.exam.model.AddPetResponse;
import com.example.exam.model.PetResponse;
import com.example.exam.service.PetPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = PetConstants.TAG_NAME, description = PetConstants.TAG_DESCRIPTION)
@RestController
@RequestMapping(PetConstants.API_BASE_PATH)
@RequiredArgsConstructor
public class PetController {

    private final PetPort petPort;

    @Operation(summary = PetConstants.GET_PET_SUMMARY, description = PetConstants.GET_PET_DESCRIPTION)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = PetConstants.DESC_PET_FOUND),
            @ApiResponse(responseCode = "404", description = PetConstants.DESC_PET_NOT_FOUND)
    })
    @GetMapping(PetConstants.PET_BY_ID_PATH)
    public PetResponse getPet(@PathVariable Long petId) {
        return petPort.getPet(petId);
    }

    @Operation(summary = PetConstants.ADD_PET_SUMMARY, description = PetConstants.ADD_PET_DESCRIPTION)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = PetConstants.DESC_PET_CREATED),
            @ApiResponse(responseCode = "400", description = PetConstants.DESC_INVALID_INPUT)
    })
    @PostMapping(PetConstants.PET_PATH)
    public ResponseEntity<AddPetResponse> addPet(@Valid @RequestBody AddPetRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petPort.addPet(request));
    }
}
