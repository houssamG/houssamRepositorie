package com.houssam.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UtilisateurDto {


    @Valid
    @NotNull(message = "La date Creation est obligatoire")
    private Instant dateCreation;

    @Valid
    @NotNull(message = "La date Modification est obligatoire")
    private Instant dateModification;
    @Valid
    @NotNull(message = "Le nom est obligatoire !")
    @NotBlank(message = "Le nom est obligatoire !")
    private String nom;
    @Valid
    @NotNull(message = "Le prenom est obligatoire !")
    @NotBlank(message = "Le prenom est obligatoire !")
    private String prenom;
    @Valid
    @NotNull(message = "Le mail est obligatoire !")
    @NotBlank(message = "Le mail est obligatoire !")
    private String mail;
    @Valid
    @NotNull(message = "La date de Naissance est obligatoire !")
    private Instant dateNaissance;
    @Valid
    @NotNull(message = "Le motDepasse est obligatoire !")
    @NotBlank(message = "Le motDepasse est obligatoire !")
    private String motDepasse;
    private String photo;
    @JsonIgnore
    private AdresseDto adresse;
    @JsonIgnore
    private EntrepriseDto entreprise;
    @JsonIgnore
    private List<RoleDto> roles;
}
