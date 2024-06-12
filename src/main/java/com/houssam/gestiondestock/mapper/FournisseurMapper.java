package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.FournisseurDto;
import com.houssam.gestiondestock.model.Fournisseur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FournisseurMapper {

    FournisseurMapper INSTANCE = Mappers.getMapper(FournisseurMapper.class);

    @Mapping(target = "commandeFournisseurs", ignore = true)
    FournisseurDto fournisseurToFournisseurDto(Fournisseur fournisseur);

    @Mapping(target = "commandeFournisseurs", ignore = true)
    Fournisseur fournisseurDtoToFournisseur(FournisseurDto fournisseurDto);
}
