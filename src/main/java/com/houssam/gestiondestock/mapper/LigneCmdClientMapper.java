package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.LigneCmdClientDto;
import com.houssam.gestiondestock.model.LigneCmdClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LigneCmdClientMapper {
    LigneCmdClientMapper INSTANCE = Mappers.getMapper(LigneCmdClientMapper.class);
    @Mapping(target = "article" , ignore = true)
    @Mapping(target = "commandeClient" , ignore = true)
    LigneCmdClientDto ligneCmdClientToLigneCmdClientDto(LigneCmdClient ligneCmdClient);
    @Mapping(target = "article" , ignore = true)
    @Mapping(target = "commandeClient" , ignore = true)
    LigneCmdClient ligneCmdClientDtoToLigneCmdClient(LigneCmdClientDto ligneCmdClientDto);
}
