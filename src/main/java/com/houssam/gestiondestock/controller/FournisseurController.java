package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.FournisseurDto;
import com.houssam.gestiondestock.service.FournisseurService;
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

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.houssam.gestiondestock.controller.ConstantsController.APP_ROOT;

@RestController
@Tag(name = "Fournisseur Apis")
public class FournisseurController {
    FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @PostMapping(value = APP_ROOT + "/fournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FournisseurDto> save(@Valid @RequestBody FournisseurDto fournisseurDto) {
        FournisseurDto fournisseur = fournisseurService.save(fournisseurDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(fournisseur);
    }

    @DeleteMapping(value = APP_ROOT + "/fournisseurs/delete/{id}")
    void delete(@PathVariable Integer id) {
        fournisseurService.delete(id);
    }

    @GetMapping(value = APP_ROOT + "/fournisseurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FournisseurDto> getReferenceById(@PathVariable Integer id) {
        FournisseurDto fournisseurDto = null;
        try {
            fournisseurDto = fournisseurService.getReferenceById(id);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fournisseurDto);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(fournisseurDto);
    }

    @GetMapping(value = APP_ROOT + "/fournisseurs/byNomAndPrenom", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FournisseurDto> findByNomAndPrenom(@RequestParam String nom, @RequestParam String prenom) {
        FournisseurDto fournisseurDto = fournisseurService.findByNomAndPrenom(nom, prenom);
        if (fournisseurDto != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(fournisseurDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fournisseurDto);
    }

    @GetMapping(value = APP_ROOT + "/fournisseurs/byDateCommandeAfter", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<FournisseurDto>> findFournisseurByDateCommandeAfter(@RequestParam Instant dateCommande) {
        List<FournisseurDto> fournisseurs = fournisseurService.findByDateCommandeAfter(dateCommande);
        if (fournisseurs.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(fournisseurs);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fournisseurs);
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
