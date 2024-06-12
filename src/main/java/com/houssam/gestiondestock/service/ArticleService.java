package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.ArticleDto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


public interface ArticleService {
    ArticleDto save(ArticleDto articleDto);
    ArticleDto update(ArticleDto articleDto);

    void delete(Integer id);

    List<ArticleDto> findByPrixunitaireTtcGreaterThan(BigDecimal prixTtc);

    ArticleDto getReferenceById(Integer id);

    List<ArticleDto> findByCodeArticleLike(String codeArticle);

    List<ArticleDto> findByDateCreationAfter(Instant dateCreation);

    List<ArticleDto> findByDateCreationBefore(Instant dateCreation);

    List<ArticleDto> findAll();

    List<ArticleDto> findArticlesByEntreprise(Integer idEntreprise);

    List<ArticleDto> findArticlesByCategorie(Integer idCategorie);

    List<ArticleDto> findArticlesByNomClient(String nomClient);

}
