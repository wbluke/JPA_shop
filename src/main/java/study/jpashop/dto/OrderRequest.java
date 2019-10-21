package study.jpashop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class OrderRequest {

    private Long memberId;
    private Long itemId;
    private int count;
}
