package study.jpashop.model;

import lombok.Getter;
import study.jpashop.model.item.Item;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private int orderPrice;
    private int count;

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.update(item, orderPrice, count);

        item.removeStock(count);
        return orderItem;
    }

    public void update(Item item, int orderPrice, int count) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public void cancel() {
        getItem().addStock(count);
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }
}
