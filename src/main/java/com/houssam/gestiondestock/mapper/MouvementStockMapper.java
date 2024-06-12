package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.MouvementStockDto;
import com.houssam.gestiondestock.model.MouvementStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MouvementStockMapper {
    MouvementStockMapper INSTANCE = Mappers.getMapper(MouvementStockMapper.class);
    @Mapping(target = "article" , ignore = true)
    MouvementStockDto mouvementStockToMouvementStockDto(MouvementStock mouvementStock);
    @Mapping(target = "article" , ignore = true)
    MouvementStock mouvementStockDtoToMouvementStock(MouvementStockDto mouvementStockDto);
}
