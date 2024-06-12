package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {
    CommandeClient save(CommandeClient article);

    CommandeClient getReferenceById(Integer id);

    CommandeClient findByCode(String code);

    List<CommandeClient> findByDateCommandeAfter(Instant dateCommande);

    @Query(value = "select cc from CommandeClient cc inner join " +
            "cc.client cl on cl.id =:idClient")
    List<CommandeClient> findCommandesClientByClientId(Integer idClient);

}
