package ru.kolesnikov.storemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikov.storemanagement.exceptions.NotFoundItemException;
import ru.kolesnikov.storemanagement.model.Items;
import ru.kolesnikov.storemanagement.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Items> getItems() {
        return itemRepository.findAll();
    }

    public Items getItemById(Long id) {

        return itemRepository.findById(id).orElseThrow(() -> new NotFoundItemException(id));
    }

    public Items addItem(Items items) {
        return itemRepository.save(items);
    }

    public Items updateItem(Long id, Items items) {
        if (!itemRepository.existsById(id)) {
            throw new NotFoundItemException(id);
        }
        return itemRepository.save(items);
    }

    public void deleteItem(Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundItemException(itemId);
        }
        itemRepository.deleteById(itemId);
    }


    public Items getItemByBarcode(String barcode) {
        return itemRepository.getItemByBarcode(barcode)
                .orElseThrow(() -> new NotFoundItemException(barcode));
    }
}
