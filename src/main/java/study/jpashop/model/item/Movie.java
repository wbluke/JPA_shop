package study.jpashop.model.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("M")
public class Movie extends Item {

    private String director;
    private String actor;

    protected Movie() {
    }

    public Movie(final String name, final int price, final int stockQuantity, final String director, final String actor) {
        super(name, price, stockQuantity);
        this.director = director;
        this.actor = actor;
    }
}
