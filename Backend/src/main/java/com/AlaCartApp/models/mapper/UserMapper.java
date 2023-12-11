package com.AlaCartApp.models.mapper;

import com.AlaCartApp.models.entity.User;
import com.AlaCartApp.models.request.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
   /* @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "state", target = "state"),
            @Mapping(source = "startDate", target = "startDate")
    })
    UserDto toUserDTO(User user);
    List<UserDto> toUsersDTO(List<User> users);
    @InheritInverseConfiguration
    User toUser(UserDto userDto);
*/

}
