package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.ArticleDto;
import com.houssam.gestiondestock.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(target = "mouvementStocks", ignore = true)
    @Mapping(target = "ligneVentes", ignore = true)
    @Mapping(target = "ligneCmdClients", ignore = true)
    @Mapping(target = "ligneCmdFournisseurs", ignore = true)
    @Mapping(target = "categorie.articles", ignore = true)
    @Mapping(target = "entreprise.utilisateurs", ignore = true)
    @Mapping(target = "entreprise.articles", ignore = true)
    @Mapping(target = "id", source = "idArticle")
    ArticleDto articleToArticleDto(Article article);

    @Mapping(target = "mouvementStocks", ignore = true)
    @Mapping(target = "ligneVentes", ignore = true)
    @Mapping(target = "ligneCmdClients", ignore = true)
    @Mapping(target = "ligneCmdFournisseurs", ignore = true)
    @Mapping(target = "categorie.articles", ignore = true)
    @Mapping(target = "entreprise.utilisateurs", ignore = true)
    @Mapping(target = "entreprise.articles", ignore = true)
    @Mapping(target = "dateCreation" , ignore = true)
    @Mapping(target = "dateModification" , ignore = true)
    @Mapping(target = "idArticle" , ignore = true)
    Article ArticleDtoToArticle(ArticleDto articleDto);
}
