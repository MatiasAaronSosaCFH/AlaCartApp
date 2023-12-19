package com.AlaCartApp.service.abstraction;

import com.AlaCartApp.models.request.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    Optional<UserDto> create (UserDto userDto);

    Optional<UserDto> update (UserDto userDto);

    Optional<List<UserDto>> userList();

    Optional<UserDto> userId (Long id);

    void delete (Long id);

    boolean isUserEmailDuplicate(String email);
}
