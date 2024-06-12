package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.VenteDto;
import com.houssam.gestiondestock.model.Vente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VenteMapper {

    VenteMapper INSTANCE = Mappers.getMapper(VenteMapper.class);

    @Mapping(target = "ligneVentes", ignore = true)
    VenteDto venteToVenteDto(Vente vente);

    @Mapping(target = "ligneVentes", ignore = true)
    Vente venteDtoToVente(VenteDto venteDto);
}
