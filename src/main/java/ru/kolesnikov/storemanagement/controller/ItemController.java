package ru.kolesnikov.storemanagement.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kolesnikov.storemanagement.controller.dto.item.ItemDTORequest;
import ru.kolesnikov.storemanagement.controller.dto.item.ItemDTOResponse;
import ru.kolesnikov.storemanagement.model.Items;
import ru.kolesnikov.storemanagement.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/v1/items")
    public List<ItemDTOResponse> getItems() {
        return itemService.getItems().stream()
                .map(item -> new ItemDTOResponse(item.getId(), item.getItemName(), item.getBarcode(), item.getPrice()))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v1/items/{itemId}")
    public ItemDTOResponse getItem(@PathVariable(name = "itemId") Long itemId) {
        Items item = itemService.getItemById(itemId);
        return new ItemDTOResponse(item.getId(), item.getItemName(), item.getBarcode(), item.getPrice());
    }

    @PostMapping("/api/v1/items")
    public ItemDTOResponse addItem(@RequestBody
                                   @NonNull ItemDTORequest itemDTORequest) {
        Items item = itemService.addItem(new Items(itemDTORequest.name(),
                itemDTORequest.barcode(), itemDTORequest.price()));
        return new ItemDTOResponse(item.getId(), item.getItemName(), item.getBarcode(), item.getPrice());
    }

    @PutMapping("/api/v1/items/{itemId}")
    public ItemDTOResponse updateItem(@PathVariable("itemId") Long itemId,
                                      @RequestBody
                                      @NonNull ItemDTORequest itemDTORequest) {

        Items item = itemService.updateItem(itemId, new Items(itemId,
                itemDTORequest.name(),
                itemDTORequest.barcode(),
                itemDTORequest.price(),
                itemService.getItemById(itemId).getBalance()));
        return new ItemDTOResponse(item.getId(), item.getItemName(), item.getBarcode(), item.getPrice());

    }

    @DeleteMapping("/api/v1/items/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }


}
