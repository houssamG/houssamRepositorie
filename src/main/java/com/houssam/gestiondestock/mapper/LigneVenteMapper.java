package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.LigneVenteDto;
import com.houssam.gestiondestock.model.LigneVente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LigneVenteMapper {
    LigneVenteMapper INSTANCE = Mappers.getMapper(LigneVenteMapper.class);
    @Mapping(target = "article" , ignore = true)
    @Mapping(target = "vente" , ignore = true)
    LigneVenteDto ligneVenteToLigneVenteDto(LigneVente ligneVente);
    @Mapping(target = "article" , ignore = true)
    @Mapping(target = "vente" , ignore = true)
    LigneVente ligneVenteDtoToLigneVente(LigneVenteDto ligneVenteDto);
}
