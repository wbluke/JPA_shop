package study.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.jpashop.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o join fetch o.member WHERE o.member.name LIKE CONCAT('%',:keyword,'%')")
    List<Order> findOrder(@Param("keyword") String keyword);
}
