package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {
    CommandeFournisseur save(CommandeFournisseur article);

    CommandeFournisseur getReferenceById(Integer id);

    CommandeFournisseur findByCode(String code);

    List<CommandeFournisseur> findByDateCommandeAfter(Instant dateCommande);

    @Query(value = "select cf from CommandeFournisseur cf inner join " +
            "cf.fournisseur f on f.id =:idFournisseur")
    List<CommandeFournisseur> findCommandesFournisseurByFournisseurId(Integer idFournisseur);
}
