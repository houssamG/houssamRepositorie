package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.AdresseDto;
import com.houssam.gestiondestock.model.Adresse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
// componentModel = "spring" pour que spring le reconnait autant que bean et pouvoir l'injecter par la suite dans nos services
public interface AdresseMapper {

    AdresseMapper INSTANCE = Mappers.getMapper(AdresseMapper.class);

    @Mapping(target = "adresse2", ignore = true)
    AdresseDto adresseToAdresseDto(Adresse adresse);

    Adresse adresseDtoToAdresse(AdresseDto adresseDto);
}
