package study.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import study.jpashop.dto.OrderRequest;
import study.jpashop.dto.OrderResponse;
import study.jpashop.model.OrderSearch;
import study.jpashop.service.OrderService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderResponse> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/search")
    public List<OrderResponse> search(OrderSearch orderSearch) {
        return orderService.search(orderSearch);
    }

    @PostMapping
    public void save(@RequestBody OrderRequest orderRequest) {
        log.info("orderRequest : {}", orderRequest);
        orderService.save(orderRequest);
    }
}
