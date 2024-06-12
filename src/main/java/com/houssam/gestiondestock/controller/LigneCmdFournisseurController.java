package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.LigneCmdFournisseurDto;
import com.houssam.gestiondestock.service.LigneCmdFournisseurService;
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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.houssam.gestiondestock.controller.ConstantsController.APP_ROOT;

@RestController
@Tag(name = "Ligne Commande Fournisseur Apis")
public class LigneCmdFournisseurController {

    @Autowired
    LigneCmdFournisseurService ligneCmdFournisseurService;

    @PostMapping(value = APP_ROOT + "/ligneCmdFournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneCmdFournisseurDto> save(@Valid @RequestBody LigneCmdFournisseurDto ligneCmdFournisseurDto) {
        LigneCmdFournisseurDto ligneCmdClient = ligneCmdFournisseurService.save(ligneCmdFournisseurDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ligneCmdClient);
    }

    @DeleteMapping(value = APP_ROOT + "/ligneCmdFournisseurs/delete/{id}")
    void delete(@PathVariable Integer id) {
        ligneCmdFournisseurService.delete(id);
    }

    @GetMapping(value = APP_ROOT + "/ligneCmdFournisseurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneCmdFournisseurDto> getReferenceById(@PathVariable Integer id) {
        LigneCmdFournisseurDto ligneCmdFournisseurDto = null;
        try {
            ligneCmdFournisseurDto = ligneCmdFournisseurService.getReferenceById(id);
        } catch (EntityNotFoundException ex) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ligneCmdFournisseurDto);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(ligneCmdFournisseurDto);
    }

    @GetMapping(value = APP_ROOT + "/ligneCmdFournisseurs/byPrixunitaireGreaterThan/{prixunitaire}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneCmdFournisseurDto>> findByPrixunitaireGreaterThan(@PathVariable BigDecimal prixunitaire) {
        List<LigneCmdFournisseurDto> ligneCmdFournisseurDtos = ligneCmdFournisseurService.findByPrixunitaireGreaterThan(prixunitaire);
        if (ligneCmdFournisseurDtos.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(ligneCmdFournisseurDtos);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ligneCmdFournisseurDtos);
    }

    @GetMapping(value = APP_ROOT + "/ligneCmdFournisseurs/byIdArticle/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneCmdFournisseurDto>> findByArticle(@PathVariable Integer idArticle) {
        List<LigneCmdFournisseurDto> ligneCmdFournisseurDtos = ligneCmdFournisseurService.findByArticle(idArticle);
        if (ligneCmdFournisseurDtos.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(ligneCmdFournisseurDtos);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ligneCmdFournisseurDtos);
    }

    @GetMapping(value = APP_ROOT + "/ligneCmdFournisseurs/byNomFournisseur/{nomFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneCmdFournisseurDto>> findLigneCmdClientByFournisseurNom(@PathVariable String nomFournisseur) {
        List<LigneCmdFournisseurDto> ligneCmdFournisseurDtos = ligneCmdFournisseurService.findLigneCmdClientByFournisseurNom(nomFournisseur);
        if (ligneCmdFournisseurDtos.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(ligneCmdFournisseurDtos);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ligneCmdFournisseurDtos);
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
