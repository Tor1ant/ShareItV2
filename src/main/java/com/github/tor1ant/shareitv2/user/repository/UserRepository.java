package com.github.tor1ant.shareitv2.user.repository;

import com.github.tor1ant.shareitv2.user.entity.UserEntity;
import java.util.List;

public interface UserRepository {

    UserEntity createUser(UserEntity user);

    UserEntity updateUser(UserEntity user);

    UserEntity getUserById(Long userId);

    void deleteUser(Long userId);

    List<UserEntity> getAllUsers();
}
