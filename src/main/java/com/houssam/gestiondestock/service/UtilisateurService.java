package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.UtilisateurDto;

import java.util.List;


public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto utilisateurDto);

    void delete(Integer id);

    UtilisateurDto getReferenceById(Integer id);

    UtilisateurDto findByNomAndPrenom(String nom, String prenom);

    List<UtilisateurDto> findUtilisateursByEntreprise(Integer idEntreprise);
}
