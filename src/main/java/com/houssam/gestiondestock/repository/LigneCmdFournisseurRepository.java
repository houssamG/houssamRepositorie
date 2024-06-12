package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.LigneCmdFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LigneCmdFournisseurRepository extends JpaRepository<LigneCmdFournisseur, Integer> {
    LigneCmdFournisseur save(LigneCmdFournisseur article);

    LigneCmdFournisseur getReferenceById(Integer id);

    List<LigneCmdFournisseur> findByPrixunitaireGreaterThan(BigDecimal prixunitaire);

    @Query(value = "select lcf from LigneCmdFournisseur lcf inner join " +
            "lcf.article ar on ar.id=:idArticle")
    List<LigneCmdFournisseur> findByArticle(Integer idArticle);

    @Query(value = "select lcf from LigneCmdFournisseur lcf inner join " +
            "CommandeFournisseur cf on cf.id = lcf.commandeFournisseur.id inner join " +
            "Fournisseur f on f.id = cf.fournisseur.id and f.nom =:nomFournisseur")
    List<LigneCmdFournisseur> findLigneCmdFournisseurByFournisseurNom(String nomFournisseur);
}
