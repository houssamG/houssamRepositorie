package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.EntrepriseDto;


public interface EntrepriseService {
    EntrepriseDto save(EntrepriseDto entrepriseDto);

    void delete(Integer id);

    EntrepriseDto getReferenceById(Integer id);

    EntrepriseDto findByNomIgnoreCase(String nom);

    EntrepriseDto findByDescriptionLike(String description);

    EntrepriseDto findByCodeFiscal(String codeFiscal);

    EntrepriseDto findByNumTel(String numTel);
}
