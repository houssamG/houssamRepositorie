package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client save(Client article);

    Client getReferenceById(Integer id);

    Client findByNomAndPrenom(String nom, String prenom);

    @Query(value = "select cl from CommandeClient cc inner join " +
            "Client cl on cl.id = cc.client.id and cc.dateCommande > :dateCommande")
    List<Client> findClientByDateCommandeAfter(Instant dateCommande);


}
