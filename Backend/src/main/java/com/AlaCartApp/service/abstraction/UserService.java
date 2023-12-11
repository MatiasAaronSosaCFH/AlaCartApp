package com.AlaCartApp.service.abstraction;

import com.AlaCartApp.models.request.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    Optional<?> create (UserDto userDto);

    Optional<?> update (UserDto userDto);

    Optional<List<?>> userList();

    Optional<?> userId (Long id);

    void delete (Long id);

    boolean isUserEmailDuplicate(String email);
}
