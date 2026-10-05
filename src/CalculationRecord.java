import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CalculationRecord {

    private final int id;
    private final Operation operation;
    private final List<BigDecimal> operands;
    private final BigDecimal result;
    private final LocalDateTime timestamp;

    public CalculationRecord(int id, Operation operation, List<BigDecimal> operands, BigDecimal result, LocalDateTime timestamp) {
        this.id = id;
        this.operation = operation;
        this.operands = operands;
        this.result = result;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public Operation getOperation() {
        return operation;
    }

    public List<BigDecimal> getOperands() {
        return operands;
    }

    public BigDecimal getResult() {
        return result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
