package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.LigneCmdClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LigneCmdClientRepository extends JpaRepository<LigneCmdClient, Integer> {

    LigneCmdClient save(LigneCmdClient article);

    LigneCmdClient getReferenceById(Integer id);

    List<LigneCmdClient> findByPrixunitaireGreaterThan(BigDecimal prixunitaire);

    @Query(value = "select lcc from LigneCmdClient lcc inner join " +
            "lcc.article ar on ar.id=:idArticle")
    List<LigneCmdClient> findByArticle(Integer idArticle);

    @Query(value = "select lc from LigneCmdClient lc inner join " +
            "CommandeClient cc on cc.id = lc.commandeClient.id inner join " +
            "Client cl on cl.id = cc.client.id and cl.nom =:nomClient")
    List<LigneCmdClient> findLigneCmdClientByClientNom(String nomClient);

}
