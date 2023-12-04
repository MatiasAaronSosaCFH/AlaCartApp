package com.AlaCartApp.models.mapper;

import com.AlaCartApp.models.request.ManagerDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    @Mappings({
            @Mapping(source = "id" , target = "id"),
            @Mapping(source = "name" , target = "name"),
            @Mapping(source = "password" , target = "password"),
            @Mapping(source = "roleAdmin" , target = "roleadmin")
    })
    ManagerDto toManagerDTO (Manager manager);
    List<ManagerDto> toManagersDTO (List <Manager> manager);
    @InheritInverseConfiguration
    Manager toManager (ManagerDto managerDto)





}