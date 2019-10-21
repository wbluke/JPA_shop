package study.jpashop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String searchKeyword;
    private OrderStatus status;
}
