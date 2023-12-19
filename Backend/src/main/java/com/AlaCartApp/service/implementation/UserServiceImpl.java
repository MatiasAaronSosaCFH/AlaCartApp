package com.AlaCartApp.service.implementation;

import com.AlaCartApp.models.entity.User;
import com.AlaCartApp.models.mapper.UserMapper;
import com.AlaCartApp.models.request.UserDto;
import com.AlaCartApp.repository.UserRepository;
import com.AlaCartApp.service.abstraction.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    @Override
    public Optional<UserDto> create(UserDto userDto) {
        return Optional.of(userMapper.toUserDTO(userRepository
                .save(userMapper.toUser(userDto))));
    }

    @Override
    public Optional<UserDto> update(UserDto userDto) {

        return Optional.of(userMapper.toUserDTO(userRepository
                .save(userMapper.toUser(userDto))));
    }

    @Override
    public Optional<List<UserDto>> userList() {
        return Optional.of(userMapper.toUsersDTO(userRepository.findAll()));
    }

    @Override
    public Optional<UserDto> userId(Long id) {
        return userRepository.findById(id).map(userMapper::toUserDTO);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean isUserEmailDuplicate(String email) {

        // Implementa la lógica para verificar si el nombre está duplicado en la base de datos
        // Puedes usar el repositorio (repository) para realizar la consulta
        // Retorna true si el nombre está duplicado, false en caso contrario
        User existingUser = userRepository.findByEmail(email);
        return existingUser != null;
    }


}
