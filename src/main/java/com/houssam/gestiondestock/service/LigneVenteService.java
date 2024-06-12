package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.LigneVenteDto;

import java.math.BigDecimal;
import java.util.List;


public interface LigneVenteService {
    LigneVenteDto save(LigneVenteDto ligneVenteDto);

    void delete(Integer id);

    LigneVenteDto getReferenceById(Integer id);

    List<LigneVenteDto> findByPrixunitaireGreaterThan(BigDecimal prixunitaire);

    List<LigneVenteDto> findByArticle(Integer idArticle);

    List<LigneVenteDto> findByVente(Integer idVente);
}
