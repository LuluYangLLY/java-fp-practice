package fp.practice;

import java.math.BigDecimal;
import java.util.function.Function;

public interface CalculateFinalPriceFunction {
    BigDecimal Calculate(Function<BigDecimal, BigDecimal> applyDiscount,
                         Function<BigDecimal, BigDecimal> applyTax,
                         BigDecimal listingPrice);
}
