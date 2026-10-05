import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NumberParser {

    private NumberParser() {
    }

    public static List<BigDecimal> parse(String rawInput) {
        String[] tokens = rawInput.split(",");

        List<BigDecimal> numbers = new ArrayList<>();
        StringBuilder invalidTokens = new StringBuilder();

        for (String token : tokens) {
            String trimmed = token.trim();
            Optional<BigDecimal> parsed = tryParse(trimmed);

            if (parsed.isPresent()) {
                numbers.add(parsed.get());
            } else {
                if (invalidTokens.length() > 0) {
                    invalidTokens.append(", ");
                }
                invalidTokens.append("'").append(trimmed).append("'");
            }
        }

        if (invalidTokens.length() > 0) {
            throw new IllegalArgumentException("Invalid number(s): " + invalidTokens);
        }
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("No numbers were provided.");
        }
        return numbers;
    }

    private static Optional<BigDecimal> tryParse(String value) {
        try {
            return Optional.of(new BigDecimal(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
