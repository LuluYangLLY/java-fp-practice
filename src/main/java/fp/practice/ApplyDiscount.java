package fp.practice;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class ApplyDiscount implements BiFunction<BigDecimal, BigDecimal, BigDecimal>  {
    public BigDecimal apply(BigDecimal rate, BigDecimal amount) {
        BigDecimal discount = amount.multiply(rate);
        return amount.add((discount).negate());
    }
}
