package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.MouvementStockDto;
import com.houssam.gestiondestock.model.TypeMouvement;
import com.houssam.gestiondestock.service.MouvementStockService;
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
@Tag(name = "Mouvement Stock Apis")
public class MouvementStockController {
    @Autowired
    MouvementStockService mouvementStockService;

    @PostMapping(value = APP_ROOT + "/mouvementStocks/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MouvementStockDto> save(@Valid @RequestBody MouvementStockDto mouvementStock) {

        MouvementStockDto res = mouvementStockService.save(mouvementStock);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping(value = APP_ROOT + "/mouvementStocks/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable Integer id) {
        mouvementStockService.delete(id);
    }

    @GetMapping(value = APP_ROOT + "/mouvementStocks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MouvementStockDto> getReferenceById(@PathVariable Integer id) {
        MouvementStockDto mouvementStock = null;
        try {
            mouvementStock = mouvementStockService.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mouvementStock);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(mouvementStock);

    }

    @GetMapping(value = APP_ROOT + "/mouvementStocks/byTypeMouvement", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<MouvementStockDto>> findByTypeMouvement(@RequestBody TypeMouvement typeMouvement) {
        List<MouvementStockDto> mouvementStocksDto = mouvementStockService.findByTypeMouvement(typeMouvement);
        if (mouvementStocksDto.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(mouvementStocksDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mouvementStocksDto);
    }

    @GetMapping(value = APP_ROOT + "/mouvementStocks/byDateMouvementGreaterThan", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<MouvementStockDto>> findByDateMouvementGreaterThan(@RequestParam Instant dateMouvement) {
        List<MouvementStockDto> mouvementStocksDto = mouvementStockService.findByDateMouvementGreaterThan(dateMouvement);
        if (mouvementStocksDto.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(mouvementStocksDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mouvementStocksDto);
    }

    @GetMapping(value = APP_ROOT + "/mouvementStocks/byArticle/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<MouvementStockDto>> findByArticle(@PathVariable Integer idArticle) {
        List<MouvementStockDto> mouvementStocksDto = mouvementStockService.findByArticle(idArticle);
        if (mouvementStocksDto.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(mouvementStocksDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mouvementStocksDto);
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
