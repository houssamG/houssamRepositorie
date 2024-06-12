package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.UtilisateurDto;
import com.houssam.gestiondestock.service.UtilisateurService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.houssam.gestiondestock.controller.ConstantsController.APP_ROOT;

@RestController
@Slf4j
@Tag(name = "Utilisateur Apis")
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;

    @PostMapping(value = APP_ROOT + "/utilisateurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDto> save(@Valid @RequestBody UtilisateurDto utilisateurDto) {

        UtilisateurDto res = utilisateurService.save(utilisateurDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable Integer id) {
        utilisateurService.delete(id);
    }

    @GetMapping(value = APP_ROOT + "/utilisateurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDto> getReferenceById(@PathVariable Integer id) {
        UtilisateurDto utilisateurDto = null;
        try {
            utilisateurDto = utilisateurService.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            log.info("NOT FOUND");
            log.trace("NOT FOUND ");
            log.debug("NOT FOUND");
            log.warn("NOT FOUND");
            log.error("NOT FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(utilisateurDto);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(utilisateurDto);

    }

    @GetMapping(value = APP_ROOT + "/utilisateurs/byNomPrenom", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDto> findByNomAndPrenom(@RequestParam String nom, @RequestParam String prenom) {
        UtilisateurDto utilisateurDto = utilisateurService.findByNomAndPrenom(nom, prenom);
        if (utilisateurDto != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(utilisateurDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(utilisateurDto);
    }

    @GetMapping(value = APP_ROOT + "/utilisateurs/byEntreprise/{idEntreprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UtilisateurDto>> findUtilisateursByEntreprise(@PathVariable Integer idEntreprise) {
        List<UtilisateurDto> utilisateursDto = utilisateurService.findUtilisateursByEntreprise(idEntreprise);
        if (utilisateursDto.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(utilisateursDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(utilisateursDto);
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
