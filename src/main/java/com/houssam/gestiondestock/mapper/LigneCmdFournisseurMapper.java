package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.LigneCmdFournisseurDto;
import com.houssam.gestiondestock.model.LigneCmdFournisseur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LigneCmdFournisseurMapper {
    LigneCmdFournisseurMapper INSTANCE = Mappers.getMapper(LigneCmdFournisseurMapper.class);

    @Mapping(target = "article" , ignore = true)
    @Mapping(target = "commandeFournisseur" , ignore = true)
    LigneCmdFournisseurDto ligneCmdFournisseurToLigneCmdFournisseurDto(LigneCmdFournisseur ligneCmdFournisseur);
    @Mapping(target = "article" , ignore = true)
    @Mapping(target = "commandeFournisseur" , ignore = true)
    LigneCmdFournisseur ligneCmdFournisseurDtoToLigneCmdFournisseur(LigneCmdFournisseurDto ligneCmdFournisseurDto);
}
