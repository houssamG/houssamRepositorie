package com.houssam.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class LigneVenteDto {


    @Valid
    @NotNull(message = "La date Creation est obligatoire")
    private Instant dateCreation;

    @Valid
    @NotNull(message = "La date Modification est obligatoire")
    private Instant dateModification;
    @Valid
    @NotNull(message = "La quantite est obligatoire !")
    private BigDecimal quantite;
    @Valid
    @NotNull(message = "Le prix est obligatoire !")
    private BigDecimal prixunitaire;
    @JsonIgnore
    private ArticleDto article;
    @JsonIgnore
    private VenteDto vente;
}
