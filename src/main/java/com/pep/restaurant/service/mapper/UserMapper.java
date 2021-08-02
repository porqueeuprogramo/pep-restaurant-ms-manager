package com.pep.restaurant.service.mapper;

import com.pep.restaurant.domain.User;
import com.pep.restaurant.service.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapUserDTOToUser(UserDTO userDTO);

    UserDTO mapUserToUserDTO(User user);

}