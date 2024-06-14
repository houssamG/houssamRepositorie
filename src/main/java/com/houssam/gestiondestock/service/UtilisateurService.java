package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.UtilisateurDto;
import com.houssam.gestiondestock.model.Utilisateur;

import java.util.List;


public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto utilisateurDto);

    void delete(Integer id);

    UtilisateurDto getReferenceById(Integer id);

    UtilisateurDto findByNomAndPrenom(String nom, String prenom);

    List<UtilisateurDto> findUtilisateursByEntreprise(Integer idEntreprise);

    UtilisateurDto findByMail(String mail);
}
