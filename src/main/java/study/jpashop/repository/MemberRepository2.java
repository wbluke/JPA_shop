package study.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpashop.model.Member;

public interface MemberRepository2 extends JpaRepository<Member, Long> {
}
