package study.jpashop.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Member extends BaseEntity {

    private String name;
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    protected Member() {
    }

    public Member(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
