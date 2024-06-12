package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.VenteDto;
import com.houssam.gestiondestock.service.VenteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.houssam.gestiondestock.controller.ConstantsController.APP_ROOT;

@RestController
@Slf4j
@Tag(name = "Vente Apis")
public class VenteController {
    @Autowired
    VenteService venteService;

    @PostMapping(value = APP_ROOT + "/ventes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VenteDto> save(@Valid @RequestBody VenteDto venteDto) {

        VenteDto res = venteService.save(venteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping(value = APP_ROOT + "/ventes/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable Integer id) {
        venteService.delete(id);
    }

    @GetMapping(value = APP_ROOT + "/ventes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VenteDto> getReferenceById(@PathVariable Integer id) {
        VenteDto venteDto = null;
        try {
            venteDto = venteService.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            log.info("NOT FOUND");
            log.trace("NOT FOUND ");
            log.debug("NOT FOUND");
            log.warn("NOT FOUND");
            log.error("NOT FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(venteDto);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(venteDto);
    }

    @GetMapping(value = APP_ROOT + "/ventes/byCode/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VenteDto> findByCode(@PathVariable String code) {
        VenteDto venteDto = venteService.findByCode(code);
        if (venteDto != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(venteDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(venteDto);
    }

    @GetMapping(value = APP_ROOT + "/ventes/byDateVenteGreaterThan", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VenteDto>> findByDateVenteGreaterThan(@RequestParam Instant dateVente) {
        List<VenteDto> ventesDto = venteService.findByDateVenteGreaterThan(dateVente);
        if (ventesDto.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(ventesDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ventesDto);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
