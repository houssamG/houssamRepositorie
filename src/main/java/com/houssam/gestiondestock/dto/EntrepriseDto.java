package com.houssam.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class EntrepriseDto {


    @Valid
    @NotNull(message = "La date Creation est obligatoire")
    private Instant dateCreation;

    @Valid
    @NotNull(message = "La date Modification est obligatoire")
    private Instant dateModification;

    @Valid
    @NotNull(message = "Le nom est obligatoire")
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    private String description;
    @JsonIgnore
    private AdresseDto adresse;
    private String codeFiscal;
    private String photo;
    private String email;
    private String numTel;
    private String siteWebUrl;
    @JsonIgnore
    private List<UtilisateurDto> utilisateurs;
    @JsonIgnore
    private List<ArticleDto> articles;

}
