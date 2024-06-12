package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.LigneCmdClientDto;

import java.math.BigDecimal;
import java.util.List;


public interface LigneCmdClientService {
    LigneCmdClientDto save(LigneCmdClientDto ligneCmdClientDto);

    void delete(Integer id);

    LigneCmdClientDto getReferenceById(Integer id);

    List<LigneCmdClientDto> findByPrixunitaireGreaterThan(BigDecimal prixunitaire);

    List<LigneCmdClientDto> findByArticle(Integer idArticle);

    List<LigneCmdClientDto> findLigneCmdClientByClientNom(String nomClient);
}
