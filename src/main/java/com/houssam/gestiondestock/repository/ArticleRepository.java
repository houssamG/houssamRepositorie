package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Article save(Article article);

    @Query("select a from Article a where a.prixunitaireTtc > :prixTtc")
    List<Article> getArticleSuperieurAprixQueryTest(BigDecimal prixTtc);

    List<Article> findByPrixunitaireTtcGreaterThan(BigDecimal prixTtc);

    Article getReferenceById(Integer id);

    List<Article> findByCodeArticleLike(String codeArticle);

    List<Article> findByDateCreationAfter(Instant dateCreation);

    List<Article> findByDateCreationBefore(Instant dateCreation);

    List<Article> findAll();

    @Query(value = "select a from Article a inner join a.entreprise e on e.id = :idEntreprise")
    List<Article> findArticlesByEntreprise(Integer idEntreprise);

    @Query(value = "select a from Article a inner join a.categorie c on c.id = :idCategorie")
    List<Article> findArticlesByCategorie(Integer idCategorie);

    @Query(value = "select a from Article a where a.id in (select lcc.article.id from LigneCmdClient lcc inner join " +
            "CommandeClient cc on cc.id = lcc.commandeClient.id inner join " +
            "Client cl on cl.id = cc.client.id inner join " +
            "Article ar on ar.id = lcc.article.id and cl.nom =:nomClient)")
    List<Article> findArticlesByNomClient(String nomClient);


}
