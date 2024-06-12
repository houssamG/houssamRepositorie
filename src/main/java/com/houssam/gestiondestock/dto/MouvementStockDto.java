package com.houssam.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.houssam.gestiondestock.model.TypeMouvement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class MouvementStockDto {


    @Valid
    @NotNull(message = "La date Creation est obligatoire")
    private Instant dateCreation;

    @Valid
    @NotNull(message = "La date Modification est obligatoire")
    private Instant dateModification;
    private Instant dateMouvement;
    @Valid
    @NotNull(message = "La quantite est obligatoire !")
    private BigDecimal quantite;
    @JsonIgnore
    private TypeMouvement typeMouvement;
    @JsonIgnore
    private ArticleDto article;
}
