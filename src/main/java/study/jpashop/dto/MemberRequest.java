package study.jpashop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequest {

    private String name;
    private String city;
    private String street;
    private String zipcode;

    public MemberRequest(final String name, final String city, final String street, final String zipcode) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
