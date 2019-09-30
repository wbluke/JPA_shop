package study.jpashop.service;

import study.jpashop.dto.MemberRequest;
import study.jpashop.dto.MemberResponse;
import study.jpashop.model.Address;
import study.jpashop.model.Member;

public class MemberAssembler {

    public static Member toEntity(MemberRequest memberRequest) {
        Address address = new Address(memberRequest.getCity(), memberRequest.getStreet(), memberRequest.getZipcode());
        return new Member(memberRequest.getName(), address);
    }

    public static MemberResponse toDto(Member member) {
        Address address = member.getAddress();
        return new MemberResponse(member.getId(), member.getName(), address.getCity(), address.getStreet(), address.getZipcode());
    }
}
