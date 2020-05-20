package fp.practice;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FpConfig {
    public Function<BigDecimal, BigDecimal> calculateConfig () {
        BigDecimal discountRate = new BigDecimal(0.2);
        BigDecimal taxRate = new BigDecimal(0.1);
        ApplyDiscount applyDiscount = new ApplyDiscount();
        ApplyTax applyTax = new ApplyTax();
        CalculateFinalPriceFunction calculateFinalPriceFunction = new CalculateFinalPriceFunctionImpl();
        return generateCurriedCalculateFinalPrice(discountRate, taxRate, applyDiscount, applyTax, calculateFinalPriceFunction);
    }

    private Function<BigDecimal, BigDecimal> generateCurriedCalculateFinalPrice(
            BigDecimal discountRate,
            BigDecimal taxRate,
            BiFunction<BigDecimal, BigDecimal, BigDecimal> applyDiscount,
            BiFunction<BigDecimal, BigDecimal, BigDecimal> applyTax,
            CalculateFinalPriceFunction calculateFinalPriceFunction
    ) {
        Function<BigDecimal, BigDecimal> applyDiscountForAmount = curry(applyDiscount).apply(discountRate);
        Function<BigDecimal, BigDecimal> applyTaxForAmount = curry(applyTax).apply(taxRate);
        Function<BigDecimal, BigDecimal> calculateFinalPriceForListingPrice = curry(calculateFinalPriceFunction)
                .apply(applyDiscountForAmount)
                .apply(applyTaxForAmount);
        return calculateFinalPriceForListingPrice;
    }

    private Function<BigDecimal, Function<BigDecimal, BigDecimal>>
    curry(BiFunction<BigDecimal, BigDecimal, BigDecimal> function) {
        return t -> u -> function.apply(t, u);
    }

    private Function<Function<BigDecimal, BigDecimal>, Function<Function<BigDecimal, BigDecimal>, Function<BigDecimal, BigDecimal>>>
    curry(CalculateFinalPriceFunction function) {
        return applyDiscountForAmount -> applyTaxForAmount -> listingPrice ->
                function.Calculate(applyDiscountForAmount, applyTaxForAmount, listingPrice);
    }

}
