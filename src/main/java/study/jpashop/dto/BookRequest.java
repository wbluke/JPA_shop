package study.jpashop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class BookRequest {
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;

    public BookRequest(final String name, final int price, final int stockQuantity, final String author, final String isbn) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }
}
