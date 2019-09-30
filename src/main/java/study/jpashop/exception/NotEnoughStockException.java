package study.jpashop.exception;

public class NotEnoughStockException extends IllegalArgumentException {
    public NotEnoughStockException(final String s) {
        super(s);
    }
}
