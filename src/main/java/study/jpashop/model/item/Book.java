package study.jpashop.model.item;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;
    private String isbn;

    protected Book() {
    }

    @Builder
    public Book(final String name, final int price, final int stockQuantity, final String author, final String isbn) {
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    public void update(final Book updateValue) {
        this.name = updateValue.name;
        this.price = updateValue.price;
        this.stockQuantity = updateValue.stockQuantity;
        this.author = updateValue.author;
        this.isbn = updateValue.isbn;
    }
}
