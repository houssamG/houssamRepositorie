package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.CommandeFournisseurDto;
import com.houssam.gestiondestock.model.CommandeFournisseur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommandeFournisseurMapper {
    CommandeFournisseurMapper INSTANCE = Mappers.getMapper(CommandeFournisseurMapper.class);


    @Mapping(target = "ligneCmdFournisseurs", ignore = true)
    @Mapping(target = "fournisseur", ignore = true)
    CommandeFournisseurDto commandeFournisseurToCommandeFournisseurDto(CommandeFournisseur commandeFournisseur);

    @Mapping(target = "ligneCmdFournisseurs", ignore = true)
    @Mapping(target = "fournisseur", ignore = true)
    CommandeFournisseur commandeFournisseurDtoToCommandeFournisseur(CommandeFournisseurDto commandeFournisseurDto);
}
