import java.math.BigDecimal;
import java.util.List;

public enum Operation {

    SUM("Sum", "+") {
        @Override
        public BigDecimal apply(List<BigDecimal> numbers) {
            return numbers.stream()
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    },

    SUBTRACT("Subtraction", "-") {
        @Override
        public BigDecimal apply(List<BigDecimal> numbers) {

            BigDecimal first = numbers.get(0);
            BigDecimal sumOfTheRest = numbers.stream()
                    .skip(1)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            return first.subtract(sumOfTheRest);
        }
    };

    private final String label;
    private final String symbol;

    Operation(String label, String symbol) {
        this.label = label;
        this.symbol = symbol;
    }

    public String getLabel() {
        return label;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract BigDecimal apply(List<BigDecimal> numbers);
}
