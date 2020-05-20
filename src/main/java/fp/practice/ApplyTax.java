package fp.practice;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class ApplyTax implements BiFunction<BigDecimal, BigDecimal, BigDecimal> {
    public BigDecimal apply(BigDecimal rate, BigDecimal amount) {
        return amount.multiply(rate).add(amount);
    }
}
