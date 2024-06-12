package com.houssam.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class FournisseurDto {


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
    @Valid
    @NotNull(message = "Le prenom est obligatoire")
    @NotBlank(message = "Le prenom est obligatoire")
    private String prenom;
    private String mail;
    private String numTel;
    private String photo;
    @JsonIgnore
    private AdresseDto adresse;
    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;
}
