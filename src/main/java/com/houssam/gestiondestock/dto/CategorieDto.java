package com.houssam.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CategorieDto {


    @Valid
    @NotNull(message = "La date Creation est obligatoire")
    private Instant dateCreation;

    @Valid
    @NotNull(message = "La date Modification est obligatoire")
    private Instant dateModification;

    @Valid
    @NotNull(message = "Le code categorie est obligatoire")
    @NotBlank(message = "Le code categorie est obligatoire")
    private String code;
    private String designation;
    @JsonIgnore // lors de la serialisation les articles ne seront pas afficher dans le json generé
    private List<ArticleDto> articles;

}
