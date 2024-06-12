package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {
    LigneVente save(LigneVente article);

    LigneVente getReferenceById(Integer id);

    List<LigneVente> findByPrixunitaireGreaterThan(BigDecimal prixunitaire);

    @Query(value = "select lv from LigneVente lv inner join " +
            "lv.article ar on ar.id=:idArticle")
    List<LigneVente> findByArticle(Integer idArticle);

    @Query(value = "select lv from LigneVente lv inner join " +
            "lv.vente v on v.id=:idVente")
    List<LigneVente> findByVente(Integer idVente);
}
