package study.jpashop.model.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
    private String etc;

    protected Album() {
    }

    public Album(final String name, final int price, final int stockQuantity, final String artist, final String etc) {
        super(name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }
}
