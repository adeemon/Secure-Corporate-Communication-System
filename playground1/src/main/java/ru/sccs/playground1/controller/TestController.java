package ru.sccs.playground1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.sccs.playground1.dto.ApartmentCreationDTO;
import ru.sccs.playground1.dto.ApartmentDTO;
import ru.sccs.playground1.dto.ResidentDTO;
import ru.sccs.playground1.mapper.ApartmentMapper;
import ru.sccs.playground1.mapper.ResidentMapper;
import ru.sccs.playground1.repository.ApartmentRepository;
import ru.sccs.playground1.repository.ResidentRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Log4j2
public class TestController implements TestAPI {

    private final ResidentMapper residentMapper;
    private final ResidentRepository residentRepository;
    private final ApartmentMapper apartmentMapper;
    private final ApartmentRepository apartmentRepository;

    @Override
    public ResponseEntity<String> getTest() {
        log.debug("DEBUG");
        log.info("INFO");
        log.warn("WARN");
        log.error("ERROR");
        log.fatal("FATAL");
        return ResponseEntity.ok("test");
    }

    @Override
    public ResponseEntity<ResidentDTO> addResident(ResidentDTO resident) {
        return ResponseEntity.ok(
                residentMapper.dtoFromEntity(
                        residentRepository.save(
                                residentMapper.entityFromDto(resident)
                        )
                )
        );
    }

    @Override
    public ResponseEntity<List<ResidentDTO>> getAllResidents() {
        return ResponseEntity.ok(
                residentRepository
                        .findAll()
                        .stream()
                        .map(residentMapper::dtoFromEntity)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<ResidentDTO> getResidentById(Long residentId) {
        return ResponseEntity.ok(
                residentMapper.dtoFromEntity(
                        residentRepository
                        .findById(residentId)
                        .orElseThrow(IllegalArgumentException::new)
                )
        );
    }

    @Override
    public ResponseEntity<ApartmentDTO> addApartment(ApartmentCreationDTO apartmentCreationDTO) {
        return ResponseEntity.ok(
                apartmentMapper.dtoFromEntity(
                        apartmentRepository.save(
                                apartmentMapper.entityFromCreationDto(apartmentCreationDTO)
                        )
                )
        );
    }

    @Override
    public ResponseEntity<ApartmentDTO> addResidentToApartment(Long apartmentId, Long residentId) {
        ApartmentDTO apartmentDTO = apartmentMapper.dtoFromEntity(
                apartmentRepository.findById(apartmentId)
                        .orElseThrow(IllegalArgumentException::new)
        );
        ResidentDTO residentDTO = residentMapper.dtoFromEntity(
                residentRepository.findById(residentId)
                        .orElseThrow(IllegalArgumentException::new)
        );
        Set<ResidentDTO> residents = apartmentDTO.getResidents();
        residents.add(residentDTO);
        apartmentDTO.setResidents(residents);
        apartmentRepository.save(apartmentMapper.entityFromDto(apartmentDTO));
        return ResponseEntity.ok(apartmentDTO);
    }
}
