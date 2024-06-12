package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.LigneVenteDto;
import com.houssam.gestiondestock.service.LigneVenteService;
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
@Tag(name = "Ligne Vente Apis")
public class LigneVenteController {

    @Autowired
    LigneVenteService ligneVenteService;

    @PostMapping(value = APP_ROOT + "/ligneVentes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneVenteDto> save(@Valid @RequestBody LigneVenteDto ligneVenteDto) {

        LigneVenteDto res = ligneVenteService.save(ligneVenteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping(value = APP_ROOT + "/ligneVentes/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable Integer id) {
        ligneVenteService.delete(id);
    }

    @GetMapping(value = APP_ROOT + "/ligneVentes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneVenteDto> getReferenceById(@PathVariable Integer id) {
        LigneVenteDto ligneVenteDto = null;
        try {
            ligneVenteDto = ligneVenteService.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ligneVenteDto);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(ligneVenteDto);

    }

    @GetMapping(value = APP_ROOT + "/ligneVentes/byPrixunitaireGreaterThan/{prixunitaire}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneVenteDto>> findByPrixunitaireGreaterThan(@PathVariable BigDecimal prixunitaire) {
        List<LigneVenteDto> lignesVenteDto = ligneVenteService.findByPrixunitaireGreaterThan(prixunitaire);
        if (lignesVenteDto.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(lignesVenteDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(lignesVenteDto);
    }

    @GetMapping(value = APP_ROOT + "/ligneVentes/byArticle/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneVenteDto>> findByArticle(@PathVariable Integer idArticle) {
        List<LigneVenteDto> lignesVenteDto = ligneVenteService.findByArticle(idArticle);
        if (lignesVenteDto.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(lignesVenteDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(lignesVenteDto);
    }

    @GetMapping(value = APP_ROOT + "/ligneVentes/byVente/{idVente}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneVenteDto>> findByVente(@PathVariable Integer idVente) {
        List<LigneVenteDto> lignesVenteDto = ligneVenteService.findByVente(idVente);
        if (lignesVenteDto.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(lignesVenteDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(lignesVenteDto);
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
