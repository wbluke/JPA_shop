package study.jpashop.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Getter
@Entity
public class Delivery extends BaseEntity {

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    protected Delivery() {
    }

    public Delivery(final Address address) {
        this.address = address;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }
}
