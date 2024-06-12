package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.CommandeFournisseurDto;
import com.houssam.gestiondestock.service.CommandeFournisseurService;
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
@Tag(name = "Commande Fournisseur Apis")
public class CommandeFournisseurController {

    CommandeFournisseurService commandeFournisseurService;

    @Autowired
    public void setCommandeFournisseurService(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @PostMapping(value = APP_ROOT + "/commandesFournisseur/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeFournisseurDto> save(@Valid @RequestBody CommandeFournisseurDto commandeFournisseurDto) {
        CommandeFournisseurDto commandeFournisseur = commandeFournisseurService.save(commandeFournisseurDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commandeFournisseur);
    }

    @DeleteMapping(value = APP_ROOT + "/commandesFournisseur/delete/{id}")
    void delete(@PathVariable Integer id) {
        commandeFournisseurService.delete(id);
    }

    @GetMapping(value = APP_ROOT + "/commandesFournisseur/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeFournisseurDto> getReferenceById(@PathVariable Integer id) {
        CommandeFournisseurDto commandeFournisseurDto = null;
        try {
            commandeFournisseurDto = commandeFournisseurService.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commandeFournisseurDto);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(commandeFournisseurDto);
    }

    @GetMapping(value = APP_ROOT + "/commandesFournisseur/byCode/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeFournisseurDto> findByCode(@PathVariable String code) {
        CommandeFournisseurDto commandeFournisseurDto = commandeFournisseurService.findByCode(code);
        if (commandeFournisseurDto != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(commandeFournisseurDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commandeFournisseurDto);
    }

    @GetMapping(value = APP_ROOT + "/commandesFournisseur/byDateCommandeAfter", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommandeFournisseurDto>> findByDateCommandeAfter(@RequestParam Instant dateCommande) {
        List<CommandeFournisseurDto> commandeFournisseurDtos = commandeFournisseurService.findByDateCommandeAfter(dateCommande);
        if (commandeFournisseurDtos.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(commandeFournisseurDtos);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commandeFournisseurDtos);
    }

    @GetMapping(value = APP_ROOT + "/commandesFournisseur/byIdFournisseur/{idFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommandeFournisseurDto>> findCommandesFournisseurByIdFournisseur(@PathVariable Integer idFournisseur) {
        List<CommandeFournisseurDto> commandeFournisseurDtos = commandeFournisseurService.findCommandesFournisseurByIdFournisseur(idFournisseur);
        if (commandeFournisseurDtos.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(commandeFournisseurDtos);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commandeFournisseurDtos);
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
