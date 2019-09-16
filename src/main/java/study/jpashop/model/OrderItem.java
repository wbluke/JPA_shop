package study.jpashop.model;

import lombok.Getter;
import lombok.Setter;
import study.jpashop.model.item.Item;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @Column(name = "ITEM_ID")
    private Item item;

    @Column(name = "ORDER_ID")
    private Order order;

    private int orderPrice;
    private int count;
}
