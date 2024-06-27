package com.github.tor1ant.shareitv2.user.service.impl;

import com.github.tor1ant.shareitv2.exception.NotFoundException;
import com.github.tor1ant.shareitv2.user.mapper.UserMapper;
import com.github.tor1ant.shareitv2.user.repository.UserRepository;
import com.github.tor1ant.shareitv2.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.generated.model.dto.UserDTO;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO user) {
        return userMapper.toDto(userRepository.createUser(userMapper.toEntity(user)));
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        return userMapper.toDto(userRepository.updateUser(userMapper.toEntity(user)));
    }

    @Override
    public UserDTO getUserById(Long userId) {
        if (userRepository.getUserById(userId) != null) {
            return userMapper.toDto(userRepository.getUserById(userId));
        }
        log.error("User with id {} not found", userId);
        throw new NotFoundException("User with id " + userId + " not found");
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteUser(userId);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userMapper.toDtoList(userRepository.getAllUsers());
    }
}
