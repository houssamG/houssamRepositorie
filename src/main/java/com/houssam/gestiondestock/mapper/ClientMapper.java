package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.ClientDto;
import com.houssam.gestiondestock.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "commandeClients", ignore = true)
    ClientDto clientToClientDto(Client client);

    @Mapping(target = "commandeClients", ignore = true)
    Client clientDtoToClient(ClientDto clientDto);
}
