package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.ArticleDto;
import com.houssam.gestiondestock.mapper.ArticleMapper;
import com.houssam.gestiondestock.model.Article;
import com.houssam.gestiondestock.repository.ArticleRepository;
import com.houssam.gestiondestock.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service("ArticleServiceImpl")
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleRepository articleRepository;

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        Article article = articleRepository.save(articleMapper.ArticleDtoToArticle(articleDto));
        return articleMapper.articleToArticleDto(article);
    }

    @Override
    public ArticleDto update(ArticleDto articleDto) {
        Article article = articleRepository.getReferenceById(articleDto.getId());
        Article articleRes = articleMapper.ArticleDtoToArticle(articleDto);
        articleRes.setIdArticle(articleDto.getId());
        articleRes.setDateCreation(article.getDateCreation());
        return articleMapper.articleToArticleDto(articleRepository.save(articleRes));
    }

    @Override
    public void delete(Integer id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<ArticleDto> findByPrixunitaireTtcGreaterThan(BigDecimal prixTtc) {
        List<Article> articles = articleRepository.findByPrixunitaireTtcGreaterThan(prixTtc);
        List<ArticleDto> articlesDtos = new ArrayList<>();
        articles.stream().forEach(article -> {
            articlesDtos.add(articleMapper.articleToArticleDto(article));
        });
        return articlesDtos;
    }


    @Override
    public ArticleDto getReferenceById(Integer id) {
        Article article = articleRepository.getReferenceById(id);
        if (article != null) {
            return articleMapper.articleToArticleDto(article);
        } else

            return null;
    }

    @Override
    public List<ArticleDto> findByCodeArticleLike(String codeArticle) {
        List<Article> articles = articleRepository.findByCodeArticleLike(codeArticle);
        List<ArticleDto> articlesDtos = new ArrayList<>();
        articles.stream().forEach(article -> {
            articlesDtos.add(articleMapper.articleToArticleDto(article));
        });
        return articlesDtos;
    }

    @Override
    public List<ArticleDto> findByDateCreationAfter(Instant dateCreation) {
        List<Article> articles = articleRepository.findByDateCreationAfter(dateCreation);
        List<ArticleDto> articlesDtos = new ArrayList<>();
        articles.stream().forEach(article -> {
            articlesDtos.add(articleMapper.articleToArticleDto(article));
        });
        return articlesDtos;
    }

    @Override
    public List<ArticleDto> findByDateCreationBefore(Instant dateCreation) {
        List<Article> articles = articleRepository.findByDateCreationBefore(dateCreation);
        List<ArticleDto> articlesDtos = new ArrayList<>();
        articles.stream().forEach(article -> {
            articlesDtos.add(articleMapper.articleToArticleDto(article));
        });
        return articlesDtos;
    }

    @Override
    public List<ArticleDto> findAll() {
        List<ArticleDto> articlesDtos = new ArrayList<>();
        try {
        List<Article> articles = articleRepository.findAll();

        articles.stream().forEach(article -> {

                articlesDtos.add(articleMapper.articleToArticleDto(article));

        });
        } catch (Exception e) {
            log.info("HH => " + e.getStackTrace());
            log.info("HH => " + e.getMessage());
            log.info("HH => " + e.getCause());

            throw new RuntimeException(e);
        }
        return articlesDtos;
    }

    @Override
    public List<ArticleDto> findArticlesByEntreprise(Integer idEntreprise) {
        List<Article> articles = articleRepository.findArticlesByEntreprise(idEntreprise);
        List<ArticleDto> articlesDtos = new ArrayList<>();
        articles.stream().forEach(article -> {
            articlesDtos.add(articleMapper.articleToArticleDto(article));
        });
        return articlesDtos;
    }

    @Override
    public List<ArticleDto> findArticlesByCategorie(Integer idCategorie) {

        List<Article> articles = articleRepository.findArticlesByCategorie(idCategorie);
        List<ArticleDto> articlesDtos = new ArrayList<>();
        articles.stream().forEach(article -> {
            articlesDtos.add(articleMapper.articleToArticleDto(article));
        });
        return articlesDtos;
    }

    @Override
    public List<ArticleDto> findArticlesByNomClient(String nomClient) {
        List<Article> articles = articleRepository.findArticlesByNomClient(nomClient);
        List<ArticleDto> articlesDtos = new ArrayList<>();
        articles.stream().forEach(article -> {
            articlesDtos.add(articleMapper.articleToArticleDto(article));
        });
        return articlesDtos;
    }
}
