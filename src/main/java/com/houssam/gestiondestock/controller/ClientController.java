package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.ClientDto;
import com.houssam.gestiondestock.service.ClientService;
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
@Tag(name = "Client Apis")
public class ClientController {
    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientDto> save(@Valid @RequestBody ClientDto clientDto) {
        ClientDto client = clientService.save(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{id}")
    void delete(@PathVariable Integer id) {
        clientService.delete(id);
    }

    @GetMapping(value = APP_ROOT + "/clients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientDto> getReferenceById(@PathVariable Integer id) {
        ClientDto clientDto = null;
        try {
            clientDto = clientService.getReferenceById(id);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clientDto);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(clientDto);
    }

    @GetMapping(value = APP_ROOT + "/clients/byNomAndPrenom", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientDto> findByNomAndPrenom(@RequestParam String nom, @RequestParam String prenom) {
        ClientDto clientDto = clientService.findByNomAndPrenom(nom, prenom);
        if (clientDto != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(clientDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clientDto);
    }

    @GetMapping(value = APP_ROOT + "/clients/byDateCommandeAfter", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ClientDto>> findClientByDateCommandeAfter(@RequestParam Instant dateCommande) {
        List<ClientDto> clients = clientService.findByDateCommandeAfter(dateCommande);
        if (clients.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(clients);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clients);
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
