package study.jpashop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.usertype.LoggableUserType;
import org.springframework.web.bind.annotation.*;
import study.jpashop.dto.BookRequest;
import study.jpashop.dto.BookResponse;
import study.jpashop.service.ItemService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<BookResponse> getPage(Long offset, Long limit) {
        log.info("offset={} limit={}", offset, limit);
        return itemService.getPage(offset, limit);
    }

    @GetMapping("/all")
    public List<BookResponse> getAllItems() {
        return itemService.getAll();
    }

    @GetMapping("/count")
    public Long getCount() {
        return itemService.getCount();
    }

    @PostMapping
    public void save(@RequestBody BookRequest bookRequest) {
        log.info("bookRequest={}", bookRequest);
        itemService.save(bookRequest);
    }

    @GetMapping("/{itemId}")
    public BookResponse getItem(@PathVariable Long itemId) {
        return itemService.findById(itemId);
    }

    @PutMapping("/{itemId}")
    public void updateItem(@PathVariable Long itemId, @RequestBody BookRequest bookRequest) {
        itemService.updateItem(itemId, bookRequest);
    }
}
