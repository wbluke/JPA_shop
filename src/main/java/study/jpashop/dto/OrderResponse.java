package study.jpashop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study.jpashop.model.OrderStatus;

@Getter
@NoArgsConstructor
public class OrderResponse {

    private Long id;
    private String memberName;
    private String orderItem;
    private int orderPrice;
    private int count;
    private OrderStatus status;
    private String simpleOrderDate;

    public OrderResponse(final Long id, final String memberName, final String orderItem, final int orderPrice, final int count, final OrderStatus status, final String simpleOrderDate) {
        this.id = id;
        this.memberName = memberName;
        this.orderItem = orderItem;
        this.orderPrice = orderPrice;
        this.count = count;
        this.status = status;
        this.simpleOrderDate = simpleOrderDate;
    }
}
