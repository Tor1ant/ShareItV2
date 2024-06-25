package com.github.tor1ant.shareitv2.item.controller;

import com.github.tor1ant.shareitv2.exception.BadRequestException;
import com.github.tor1ant.shareitv2.item.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.generated.api.ItemsApi;
import ru.yandex.practicum.generated.model.dto.ItemDTO;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController implements ItemsApi {

    private final ItemService itemService;

    @Override
    public ResponseEntity<ItemDTO> createItem(Long xSharerUserId, ItemDTO itemDTO) {
        log.info("Запрос на создание item={} от пользователя с id={}", itemDTO, xSharerUserId);
        checkCreatedItem(itemDTO);
        ItemDTO createdItem = itemService.createItem(xSharerUserId, itemDTO);
        log.info("Создан item={} для пользователя с id={}", createdItem, xSharerUserId);
        return ResponseEntity.status(201).body(createdItem);
    }

    @Override
    public ResponseEntity<List<ItemDTO>> getAllItems(Long xSharerUserId) {
        log.info("Запрос на получение всех items для пользователя с id={}", xSharerUserId);
        List<ItemDTO> items = itemService.searchItems(xSharerUserId);
        log.info("Получены items={} для пользователя с id={}", items, xSharerUserId);
        return ResponseEntity.ok(items);
    }

    @Override
    public ResponseEntity<ItemDTO> getItem(Long xSharerUserId, Long itemId) {
        log.info("Запрос на получение item={} для пользователя с id={}", itemId, xSharerUserId);
        ItemDTO item = itemService.getItem(xSharerUserId, itemId);
        log.info("Получен item={} для пользователя с id={}", item, xSharerUserId);
        return ResponseEntity.ok(item);
    }

    @Override
    public ResponseEntity<List<ItemDTO>> searchItems(Long xSharerUserId, String search) {
        log.info("Запрос на поиск items={} для пользователя с id={}", search, xSharerUserId);
        List<ItemDTO> items = itemService.searchItems(xSharerUserId, search);
        log.info("Найдены items={} для пользователя с id={}", items, xSharerUserId);
        return ResponseEntity.ok(items);
    }

    @Override
    public ResponseEntity<ItemDTO> updateItem(Long xSharerUserId, Long itemId, ItemDTO itemDTO) {
        log.info("Запрос на обновление item={} для пользователя с id={}", itemId, xSharerUserId);
        ItemDTO updatedItem = itemService.updateItem(xSharerUserId, itemId, itemDTO);
        log.info("Обновлен item={} для пользователя с id={}", updatedItem, xSharerUserId);
        return ResponseEntity.ok(updatedItem);
    }

    private void checkCreatedItem(ItemDTO itemDTO) {
        if (itemDTO.getAvailable() == null
            || itemDTO.getName() == null
            || itemDTO.getName().isBlank()
            || itemDTO.getDescription() == null
            || itemDTO.getDescription().isBlank()) {
            throw new BadRequestException("Необходимо заполнить все обязательные поля");
        }
    }
}

