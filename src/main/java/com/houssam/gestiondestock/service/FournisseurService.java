package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.FournisseurDto;

import java.time.Instant;
import java.util.List;


public interface FournisseurService {

    FournisseurDto save(FournisseurDto fournisseurDto);

    void delete(Integer id);

    FournisseurDto getReferenceById(Integer id);

    FournisseurDto findByNomAndPrenom(String nom, String prenom);

    List<FournisseurDto> findByDateCommandeAfter(Instant dateCommande);
}
