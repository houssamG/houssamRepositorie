package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.CategorieDto;
import com.houssam.gestiondestock.model.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategorieMapper {
    CategorieMapper INSTANCE = Mappers.getMapper(CategorieMapper.class);

    @Mapping(target = "articles", ignore = true)
    CategorieDto categorieToCategorieDto(Categorie categorie);

    @Mapping(target = "articles", ignore = true)
    Categorie categorieDtoToCategorie(CategorieDto categorieDto);
}
