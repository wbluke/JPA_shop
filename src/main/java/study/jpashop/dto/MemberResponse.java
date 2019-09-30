package study.jpashop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponse {

    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    public MemberResponse(final Long id, final String name, final String city, final String street, final String zipcode) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
