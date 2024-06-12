package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.MouvementStock;
import com.houssam.gestiondestock.model.TypeMouvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface MouvementStockRepository extends JpaRepository<MouvementStock, Integer> {
    MouvementStock save(MouvementStock article);

    MouvementStock getReferenceById(Integer id);

    List<MouvementStock> findByTypeMouvement(TypeMouvement typeMouvement);

    List<MouvementStock> findByDateMouvementGreaterThan(Instant dateMouvement);

    @Query(value = "select mv from MouvementStock mv inner join " +
            "mv.article ar on ar.id =:idArticle")
    List<MouvementStock> findByArticle(Integer idArticle);

}
