package com.github.tor1ant.shareitv2.item.service.impl;

import com.github.tor1ant.shareitv2.item.entity.ItemEntity;
import com.github.tor1ant.shareitv2.item.mapper.ItemMapper;
import com.github.tor1ant.shareitv2.item.repository.ItemRepository;
import com.github.tor1ant.shareitv2.item.service.ItemService;
import com.github.tor1ant.shareitv2.user.entity.UserEntity;
import com.github.tor1ant.shareitv2.user.mapper.UserMapper;
import com.github.tor1ant.shareitv2.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.generated.model.dto.ItemDTO;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    @Override
    public ItemDTO createItem(Long xSharerUserId, ItemDTO itemDTO) {
        UserEntity user = getUser(xSharerUserId);
        ItemEntity entity = itemMapper.toEntity(itemDTO);
        entity.setOwner(user);
        return itemMapper.toDto(itemRepository.createItem(entity));
    }

    @Override
    public List<ItemDTO> getUserItems(Long xSharerUserId) {
        return itemMapper.toDto(itemRepository.getAllItems(xSharerUserId));
    }

    @Override
    public List<ItemDTO> searchItems(Long xSharerUserId, String search) {
        return itemMapper.toDto(itemRepository.searchItems(xSharerUserId, search));
    }

    @Override
    public ItemDTO getItem(Long xSharerUserId, Long itemId) {
        getUser(xSharerUserId);
        return itemMapper.toDto(itemRepository.getItem(itemId));
    }

    @Override
    public ItemDTO updateItem(Long xSharerUserId, Long itemId, ItemDTO itemDTO) {
        UserEntity user = getUser(xSharerUserId);
        ItemEntity entity = itemMapper.toEntity(itemDTO);
        entity.setId(itemId);
        entity.setOwner(user);
        return itemMapper.toDto(itemRepository.updateItem(entity));
    }

    private UserEntity getUser(Long xSharerUserId) {
        return userMapper.toEntity(userMapper.toDto(userRepository.getUserById(xSharerUserId)));
    }
}
