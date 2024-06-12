package com.houssam.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ArticleDto {

    private Integer id;
    private Instant dateCreation;

    private Instant dateModification;
    @Valid
    @NotNull(message = "Le code article est obligatoire")
    @NotBlank(message = "Le code article est obligatoire")
    private String codeArticle;
    private String designation;
    @Valid
    @NotNull(message = "Le prix HT est obligatoire")
    private BigDecimal prixunitaireHT;
    @Valid
    @NotNull(message = "Le tauxTva est obligatoire")
    private BigDecimal tauxTva;
    private BigDecimal prixunitaireTtc;
    private String photo;
    @JsonIgnore
    private CategorieDto categorie;
    @JsonIgnore
    private EntrepriseDto entreprise;
    @JsonIgnore
    private List<MouvementStockDto> mouvementStocks;
    @JsonIgnore
    private List<LigneVenteDto> ligneVentes;
    @JsonIgnore
    private List<LigneCmdClientDto> ligneCmdClients;
    @JsonIgnore
    private List<LigneCmdFournisseurDto> ligneCmdFournisseurs;
}
