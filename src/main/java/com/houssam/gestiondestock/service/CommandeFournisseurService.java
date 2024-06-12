package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.CommandeFournisseurDto;

import java.time.Instant;
import java.util.List;


public interface CommandeFournisseurService {
    CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto);

    void delete(Integer id);

    CommandeFournisseurDto getReferenceById(Integer id);

    CommandeFournisseurDto findByCode(String code);

    List<CommandeFournisseurDto> findByDateCommandeAfter(Instant dateCommande);

    List<CommandeFournisseurDto> findCommandesFournisseurByIdFournisseur(Integer idFournisseur);
}
