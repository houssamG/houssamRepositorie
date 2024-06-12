package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.LigneCmdFournisseurDto;

import java.math.BigDecimal;
import java.util.List;


public interface LigneCmdFournisseurService {
    LigneCmdFournisseurDto save(LigneCmdFournisseurDto ligneCmdFournisseurDto);

    void delete(Integer id);

    LigneCmdFournisseurDto getReferenceById(Integer id);

    List<LigneCmdFournisseurDto> findByPrixunitaireGreaterThan(BigDecimal prixunitaire);

    List<LigneCmdFournisseurDto> findByArticle(Integer idArticle);

    List<LigneCmdFournisseurDto> findLigneCmdClientByFournisseurNom(String nomFournisseur);
}
