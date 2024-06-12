package com.houssam.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CommandeFournisseurDto {
    public static final String LE_CODE_EST_OBLIGATOIRE = "Le code est obligatoire";
    public static final String L_ID_EST_OBLIGATOIRE = "L'id est obligatoire";

    @Valid
    @NotNull(message = "La date Creation est obligatoire")
    private Instant dateCreation;

    @Valid
    @NotNull(message = "La date Modification est obligatoire")
    private Instant dateModification;
    @Valid
    @NotNull(message = LE_CODE_EST_OBLIGATOIRE)
    @NotBlank(message = LE_CODE_EST_OBLIGATOIRE)
    private String code;
    private Instant dateCommande;
    @JsonIgnore
    private List<LigneCmdFournisseurDto> ligneCmdFournisseurs;

    @JsonIgnore
    private FournisseurDto fournisseur;

}
