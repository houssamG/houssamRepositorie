package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.CategorieDto;
import com.houssam.gestiondestock.response.ResponseHandler;
import com.houssam.gestiondestock.service.CategorieService;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.houssam.gestiondestock.controller.ConstantsController.APP_ROOT;

@RestController
@Slf4j
@Tag(name = "Categorie Apis")
public class CategorieController {

    @Autowired
    CategorieService categorieService;

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> save(@Valid @RequestBody CategorieDto categorieDto) {
        CategorieDto categorie = null;
        try {
            categorie = categorieService.save(categorieDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseHandler.generateResponse("La categorie n'a pas été crée" , HttpStatus.INTERNAL_SERVER_ERROR , categorie);
        }

        return ResponseHandler.generateResponse("La categorie a bien été crée" , HttpStatus.CREATED , categorie);
    }

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategorie}")
    ResponseEntity<Object> delete(@PathVariable Integer idCategorie) {
        try {
            categorieService.delete(idCategorie);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseHandler.generateResponse("La categorie n'a pas été supprimé" , HttpStatus.INTERNAL_SERVER_ERROR , null);
        }
        return ResponseHandler.generateResponse("La categorie a bien été supprimé" , HttpStatus.OK , null);

    }

    @GetMapping(value = APP_ROOT + "/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getReferenceById(@PathVariable Integer id) {
        CategorieDto categorieDto = null;
        try {
            categorieDto = categorieService.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            return ResponseHandler.generateResponse("La categorie n'a pas été trouvé." , HttpStatus.NOT_FOUND , categorieDto);
        }
        return ResponseHandler.generateResponse("La categorie a été trouvé." , HttpStatus.FOUND , categorieDto);
    }

    @GetMapping(value = APP_ROOT + "/categories/byCode/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> findByCode(@PathVariable String code) {
        CategorieDto categorie = categorieService.findByCode(code);
        if (categorie != null) {
            return ResponseHandler.generateResponse("La categorie a été trouvé." , HttpStatus.FOUND , categorie);
        }
        return ResponseHandler.generateResponse("La categorie n'a pas été trouvé." , HttpStatus.NOT_FOUND , categorie);

    }

    @GetMapping(value = APP_ROOT + "/categories/byDesignation/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Object>> findByDesignationLike(@PathVariable String designation) {
        List<CategorieDto> categories = categorieService.findByDesignationLike("%" + designation + "%");
        if (categories.size() > 0) {
            return ResponseHandler.generateListResponse("Categories Trouvées" ,HttpStatus.FOUND , Collections.singletonList(categories));
        } else
            return ResponseHandler.generateListResponse("Categories non Trouvées" ,HttpStatus.NOT_FOUND , Collections.singletonList(categories));
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
