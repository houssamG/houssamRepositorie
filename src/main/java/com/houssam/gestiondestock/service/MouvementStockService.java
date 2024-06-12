package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.MouvementStockDto;
import com.houssam.gestiondestock.model.TypeMouvement;

import java.time.Instant;
import java.util.List;


public interface MouvementStockService {
    MouvementStockDto save(MouvementStockDto mouvementStockDto);

    void delete(Integer id);

    MouvementStockDto getReferenceById(Integer id);

    List<MouvementStockDto> findByTypeMouvement(TypeMouvement typeMouvement);

    List<MouvementStockDto> findByDateMouvementGreaterThan(Instant dateMouvement);

    List<MouvementStockDto> findByArticle(Integer idArticle);
}
