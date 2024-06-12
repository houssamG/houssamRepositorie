package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.EntrepriseDto;
import com.houssam.gestiondestock.model.Entreprise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntrepriseMapper {
    EntrepriseMapper INSTANCE = Mappers.getMapper(EntrepriseMapper.class);

    @Mapping(target = "utilisateurs", ignore = true)
    @Mapping(target = "articles", ignore = true)
    EntrepriseDto entrepriseToEntrepriseDto(Entreprise entreprise);

    @Mapping(target = "utilisateurs", ignore = true)
    @Mapping(target = "articles", ignore = true)
    Entreprise entrepriseDtoToEntreprise(EntrepriseDto entrepriseDto);
}
