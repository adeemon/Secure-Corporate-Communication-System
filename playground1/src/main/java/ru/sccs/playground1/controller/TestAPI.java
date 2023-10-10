package ru.sccs.playground1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.sccs.playground1.dto.ApartmentCreationDTO;
import ru.sccs.playground1.dto.ApartmentDTO;
import ru.sccs.playground1.dto.ResidentDTO;

import java.util.List;

@Tag(name = "yo", description = "yo meths")
public interface TestAPI {

    @Operation(summary = "asda", description = "desc")
    @GetMapping("/test")
    ResponseEntity<String> getTest();

    @Operation(summary = "add resident", description = "adds supplied resident to db")
    @PostMapping("/residents")
    ResponseEntity<ResidentDTO> addResident(@RequestBody ResidentDTO resident);

    @Operation(summary = "get residents", description = "get all residents")
    @GetMapping("/residents")
    ResponseEntity<List<ResidentDTO>> getAllResidents();

    @Operation(summary = "get resident", description = "get resident by id")
    @GetMapping("/residents/{residentId}")
    ResponseEntity<ResidentDTO> getResidentById(@PathVariable Long residentId);

    @Operation(summary = "add apartmentCreationDTO", description = "add supplied apartmentCreationDTO to db")
    @PostMapping("/apartments")
    ResponseEntity<ApartmentDTO> addApartment(@RequestBody ApartmentCreationDTO apartmentCreationDTO);

    @Operation(summary = "add resident to apartment", description = "add supplied resident to apartment")
    @PostMapping("/apartments/{apartmentId}/add/{residentId}")
    ResponseEntity<ApartmentDTO> addResidentToApartment(@PathVariable Long apartmentId, @PathVariable Long residentId);
}
