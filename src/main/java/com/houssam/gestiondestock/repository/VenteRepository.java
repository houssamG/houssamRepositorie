package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Integer> {
    Vente save(Vente Utilisateur);

    Vente getReferenceById(Integer id);

    Vente findByCode(String code);

    List<Vente> findByDateVenteGreaterThan(Instant dateVente);

}
