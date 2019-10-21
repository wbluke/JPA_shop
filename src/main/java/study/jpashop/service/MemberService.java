package study.jpashop.service;

import org.springframework.stereotype.Service;
import study.jpashop.dto.MemberRequest;
import study.jpashop.dto.MemberResponse;
import study.jpashop.model.Member;
import study.jpashop.repository.MemberRepository2;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository2 memberRepository;

    public MemberService(final MemberRepository2 memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void save(final MemberRequest memberRequest) {
        Member newMember = MemberAssembler.toEntity(memberRequest);

        memberRepository.save(newMember);
    }

    public List<MemberResponse> getAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(MemberAssembler::toDto)
                .collect(Collectors.toList());
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
