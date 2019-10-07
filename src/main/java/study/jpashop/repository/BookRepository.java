package study.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpashop.model.item.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByIdBetween(final Long id, final Long id2);
}
