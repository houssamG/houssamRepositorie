package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.CommandeClientDto;
import com.houssam.gestiondestock.service.CommandeClientService;
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
@Tag(name = "Commande Client Apis")
public class CommandeClientController {
    @Autowired
    CommandeClientService commandeClientService;

    @PostMapping(value = APP_ROOT + "/commandesClient/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> save(@Valid @RequestBody CommandeClientDto commandeClientDto) {
        CommandeClientDto commandeClient = commandeClientService.save(commandeClientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commandeClient);
    }

    @DeleteMapping(value = APP_ROOT + "/commandesClient/delete/{id}")
    void delete(@PathVariable Integer id) {
        commandeClientService.delete(id);
    }

    @GetMapping(value = APP_ROOT + "/commandesClient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> getReferenceById(@PathVariable Integer id) {
        CommandeClientDto commandeClientDto = null;
        try {
            commandeClientDto = commandeClientService.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commandeClientDto);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(commandeClientDto);
    }

    @GetMapping(value = APP_ROOT + "/commandesClient/byCode/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable String code) {
        CommandeClientDto commandeClientDto = commandeClientService.findByCode(code);
        if (commandeClientDto != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(commandeClientDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commandeClientDto);
    }

    @GetMapping(value = APP_ROOT + "/commandesClient/byDateCommandeAfter", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommandeClientDto>> findByDateCommandeAfter(@RequestParam Instant dateCommande) {
        List<CommandeClientDto> commandeClientDtos = commandeClientService.findByDateCommandeAfter(dateCommande);
        if (commandeClientDtos.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(commandeClientDtos);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commandeClientDtos);
    }

    @GetMapping(value = APP_ROOT + "/commandesClient/byClientId/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommandeClientDto>> findCommandesClientByClientId(@PathVariable Integer idClient) {
        List<CommandeClientDto> commandeClientDtos = commandeClientService.findCommandesClientByClientId(idClient);
        if (commandeClientDtos.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(commandeClientDtos);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commandeClientDtos);
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
