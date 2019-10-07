package study.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.jpashop.dto.BookRequest;
import study.jpashop.dto.BookResponse;
import study.jpashop.model.item.Book;
import study.jpashop.repository.BookRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final BookRepository bookRepository;

    public void save(final BookRequest bookRequest) {
        bookRepository.save(BookAssembler.toEntity(bookRequest));
    }

    public List<BookResponse> getAll() {
        return bookRepository.findAll().stream()
                .map(BookAssembler::toResponse)
                .collect(Collectors.toList());
    }

    public BookResponse findById(Long id) {
        Book book = getBook(id);
        return BookAssembler.toResponse(book);
    }

    private Book getBook(final Long itemId) {
        return bookRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void updateItem(final Long itemId, final BookRequest bookRequest) {
        Book book = getBook(itemId);
        Book updateValue = BookAssembler.toEntity(bookRequest);
        book.update(updateValue);
    }

    public List<BookResponse> getPage(final Long offset, final Long limit) {
        return bookRepository.findByIdBetween(offset, offset + limit - 1).stream()
                .map(BookAssembler::toResponse)
                .collect(Collectors.toList());
    }

    public Long getCount() {
        return bookRepository.count();
    }

    public static class BookAssembler {
        public static Book toEntity(BookRequest bookRequest) {
            return Book.builder()
                    .name(bookRequest.getName())
                    .price(bookRequest.getPrice())
                    .stockQuantity(bookRequest.getStockQuantity())
                    .author(bookRequest.getAuthor())
                    .isbn(bookRequest.getIsbn())
                    .build();
        }

        public static BookResponse toResponse(Book book) {
            return BookResponse.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .price(book.getPrice())
                    .stockQuantity(book.getStockQuantity())
                    .author(book.getAuthor())
                    .isbn(book.getIsbn())
                    .build();
        }
    }
}
