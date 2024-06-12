package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
    Fournisseur save(Fournisseur fournisseur);

    Fournisseur getReferenceById(Integer id);

    Fournisseur findByNomAndPrenom(String nom, String prenom);

    @Query(value = "select f from CommandeFournisseur cf inner join " +
            "Fournisseur f on f.id = cf.fournisseur.id and cf.dateCommande > :dateCommande")
    List<Fournisseur> findFournisseurByDateCommandeAfter(Instant dateCommande);


}
