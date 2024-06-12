package com.houssam.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class AdresseDto {
    @Valid
    @NotNull(message = "L'adresse est obligatoire!")
    @NotEmpty(message = "L'adresse est obligatoire!")
    private String adresse1;
    @JsonIgnore
    private String adresse2;
    private String codePostale;
    private String ville;
    private String pays;


}
