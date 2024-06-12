package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.LigneCmdClientDto;
import com.houssam.gestiondestock.service.LigneCmdClientService;
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
@Tag(name = "Ligne Commande Client Apis")
public class LigneCmdClientController {
    @Autowired
    LigneCmdClientService ligneCmdClientService;

    @PostMapping(value = APP_ROOT + "/ligneCmdClients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneCmdClientDto> save(@Valid @RequestBody LigneCmdClientDto ligneCmdClientDto) {
        LigneCmdClientDto ligneCmdClient = ligneCmdClientService.save(ligneCmdClientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ligneCmdClient);
    }

    @DeleteMapping(value = APP_ROOT + "/ligneCmdClients/delete/{id}")
    void delete(@PathVariable Integer id) {
        ligneCmdClientService.delete(id);
    }

    @GetMapping(value = APP_ROOT + "/ligneCmdClients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneCmdClientDto> getReferenceById(@PathVariable Integer id) {
        LigneCmdClientDto ligneCmdClientDto = null;
        try {
            ligneCmdClientDto = ligneCmdClientService.getReferenceById(id);
        } catch (EntityNotFoundException ex) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ligneCmdClientDto);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(ligneCmdClientDto);
    }

    @GetMapping(value = APP_ROOT + "/ligneCmdClients/byPrixunitaireGreaterThan/{prixunitaire}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneCmdClientDto>> findByPrixunitaireGreaterThan(@PathVariable BigDecimal prixunitaire) {
        List<LigneCmdClientDto> ligneCmdClientDtos = ligneCmdClientService.findByPrixunitaireGreaterThan(prixunitaire);
        if (ligneCmdClientDtos.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(ligneCmdClientDtos);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ligneCmdClientDtos);
    }

    @GetMapping(value = APP_ROOT + "/ligneCmdClients/byIdArticle/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneCmdClientDto>> findByArticle(@PathVariable Integer idArticle) {
        List<LigneCmdClientDto> ligneCmdClientDtos = ligneCmdClientService.findByArticle(idArticle);
        if (ligneCmdClientDtos.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(ligneCmdClientDtos);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ligneCmdClientDtos);
    }

    @GetMapping(value = APP_ROOT + "/ligneCmdClients/byNomClient/{nomClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneCmdClientDto>> findLigneCmdClientByClientNom(@PathVariable String nomClient) {
        List<LigneCmdClientDto> ligneCmdClientDtos = ligneCmdClientService.findLigneCmdClientByClientNom(nomClient);
        if (ligneCmdClientDtos.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(ligneCmdClientDtos);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ligneCmdClientDtos);
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
