package study.jpashop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.jpashop.dto.OrderRequest;
import study.jpashop.dto.OrderResponse;
import study.jpashop.model.*;
import study.jpashop.model.item.Item;
import study.jpashop.repository.OrderRepository;
import study.jpashop.utils.DateFormatter;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final ItemService itemService;

    public OrderService(final OrderRepository orderRepository, final MemberService memberService, final ItemService itemService) {
        this.orderRepository = orderRepository;
        this.memberService = memberService;
        this.itemService = itemService;
    }

    public void save(OrderRequest orderRequest) {
        Member member = memberService.findById(orderRequest.getMemberId());
        Delivery delivery = new Delivery(member.getAddress());

        Item item = itemService.findItemById(orderRequest.getItemId());
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), orderRequest.getCount());

        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);
    }

    public List<OrderResponse> getAll() {
        List<Order> orders = orderRepository.findAll();
        log.info("orders : {}", orders);

        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public OrderResponse toDto(Order order) {
        OrderItem orderItem = order.getOrderItems().get(0);
        String itemName = orderItem.getItem().getName();
        return new OrderResponse(order.getId(), order.getMember().getName(), itemName, order.getTotalPrice(), orderItem.getCount(), order.getStatus(), DateFormatter.formatToSimpleDate(order.getOrderDate()));
    }

    public List<OrderResponse> search(final OrderSearch orderSearch) {
        return orderRepository.findOrder(orderSearch.getSearchKeyword())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
