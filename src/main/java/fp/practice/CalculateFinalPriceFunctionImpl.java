package fp.practice;

import java.math.BigDecimal;
import java.util.function.Function;

public class CalculateFinalPriceFunctionImpl implements CalculateFinalPriceFunction {

    @Override
    public BigDecimal Calculate (Function<BigDecimal, BigDecimal> applyDiscount, Function<BigDecimal, BigDecimal> applyTax, BigDecimal listingPrice) {
        return applyTax.compose(applyDiscount).apply(listingPrice);
    }

}
