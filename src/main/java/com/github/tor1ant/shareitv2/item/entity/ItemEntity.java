package com.github.tor1ant.shareitv2.item.entity;

import com.github.tor1ant.shareitv2.user.entity.UserEntity;
import lombok.Data;

@Data
public class ItemEntity {

    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private UserEntity owner;
    private String request;
}
