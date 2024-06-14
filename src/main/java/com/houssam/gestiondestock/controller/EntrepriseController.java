package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.EntrepriseDto;
import com.houssam.gestiondestock.service.EntrepriseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.houssam.gestiondestock.controller.ConstantsController.APP_ROOT;
@Tag(name = "Entreprise Apis")
@RestController
public class EntrepriseController {
    @Autowired
    EntrepriseService entrepriseService;

    @PostMapping(value = APP_ROOT + "/entreprises/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EntrepriseDto> save(@Valid @RequestBody EntrepriseDto entrepriseDto) {
        EntrepriseDto entreprise = entrepriseService.save(entrepriseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(entreprise);
    }

    @DeleteMapping(value = APP_ROOT + "/entreprises/delete/{id}")
    void delete(@PathVariable Integer id) {
        entrepriseService.delete(id);
    }

    @GetMapping(value = APP_ROOT + "/entreprises/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EntrepriseDto> getReferenceById(@PathVariable Integer id) {
        EntrepriseDto entrepriseDto = null;
        try {
            entrepriseDto = entrepriseService.getReferenceById(id);
        } catch (EntityNotFoundException ex) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(entrepriseDto);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(entrepriseDto);
    }

    @GetMapping(value = APP_ROOT + "/entreprises/byNom/{nom}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EntrepriseDto> findByNomIgnoreCase(@PathVariable String nom) {
        EntrepriseDto entrepriseDto = entrepriseService.findByNomIgnoreCase(nom);
        if (entrepriseDto != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(entrepriseDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entrepriseDto);
    }

    @GetMapping(value = APP_ROOT + "/entreprises/byDescription/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EntrepriseDto> findByDescriptionLike(@PathVariable String description) {
        EntrepriseDto entrepriseDto = entrepriseService.findByDescriptionLike(description);
        if (entrepriseDto != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(entrepriseDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entrepriseDto);
    }

    @GetMapping(value = APP_ROOT + "/entreprises/byCodeFiscal/{codeFiscal}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EntrepriseDto> findByCodeFiscal(@PathVariable String codeFiscal) {
        EntrepriseDto entrepriseDto = entrepriseService.findByCodeFiscal(codeFiscal);
        if (entrepriseDto != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(entrepriseDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entrepriseDto);
    }

    @GetMapping(value = APP_ROOT + "/entreprises/byNumTel/{numTel}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EntrepriseDto> findByNumTel(@PathVariable String numTel) {
        EntrepriseDto entrepriseDto = entrepriseService.findByNumTel(numTel);
        if (entrepriseDto != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(entrepriseDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entrepriseDto);
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
