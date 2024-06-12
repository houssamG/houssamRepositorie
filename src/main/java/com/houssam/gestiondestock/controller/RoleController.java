package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.RoleDto;
import com.houssam.gestiondestock.service.RoleService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.houssam.gestiondestock.controller.ConstantsController.APP_ROOT;

@RestController
@Tag(name = "Role Apis")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping(value = APP_ROOT + "/roles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RoleDto> save(@Valid @RequestBody RoleDto roleDto) {

        RoleDto res = roleService.save(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping(value = APP_ROOT + "/roles/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable Integer id) {
        roleService.delete(id);
    }

    @GetMapping(value = APP_ROOT + "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RoleDto> getReferenceById(@PathVariable Integer id) {
        RoleDto roleDto = null;
        try {
            roleDto = roleService.getReferenceById(id);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(roleDto);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(roleDto);

    }

    @GetMapping(value = APP_ROOT + "/roles/byNom/{nom}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RoleDto> findByNom(@PathVariable String nom) {
        RoleDto roleDto = roleService.findByNom(nom);
        if (roleDto != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(roleDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(roleDto);
    }

    @GetMapping(value = APP_ROOT + "/roles/byUtilisateur/{idUtilisateur}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<RoleDto>> findRolesByUtilisateur(@PathVariable Integer idUtilisateur) {
        List<RoleDto> rolesDto = roleService.findRolesByUtilisateur(idUtilisateur);
        if (rolesDto.size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(rolesDto);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rolesDto);
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
