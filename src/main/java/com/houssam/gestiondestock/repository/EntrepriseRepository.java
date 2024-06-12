package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
    Entreprise save(Entreprise article);

    Entreprise getReferenceById(Integer id);

    Entreprise findByNomIgnoreCase(String nom);

    Entreprise findByDescriptionLike(String description);

    Entreprise findByCodeFiscal(String codeFiscal);

    Entreprise findByNumTel(String numTel);
}
