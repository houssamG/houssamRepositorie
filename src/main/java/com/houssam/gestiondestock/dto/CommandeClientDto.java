package com.houssam.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CommandeClientDto {


    @Valid
    @NotNull(message = "La date Creation est obligatoire")
    private Instant dateCreation;

    @Valid
    @NotNull(message = "La date Modification est obligatoire")
    private Instant dateModification;
    @Valid
    @NotNull(message = "Le code est obligatoire")
    @NotBlank(message = "Le code est obligatoire")
    private String code;
    private Instant dateCommande;
    @JsonIgnore
    private ClientDto client;
    @JsonIgnore
    private List<LigneCmdClientDto> ligneCmdClients;
}
