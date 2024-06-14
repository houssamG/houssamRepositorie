package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.UtilisateurDto;
import com.houssam.gestiondestock.model.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "entreprise", ignore = true)
    @Mapping(target = "motDepasse" , ignore = true)
    UtilisateurDto utilisateurToUtilisateurDto(Utilisateur utilisateur);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "entreprise", ignore = true)
    Utilisateur utilisateurDtoToUtilisateur(UtilisateurDto utilisateurDto);
}
