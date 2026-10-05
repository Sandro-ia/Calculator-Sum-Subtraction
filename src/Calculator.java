import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Calculator {

    private final HistoryRepository<Integer, CalculationRecord> history = new HistoryRepository<>();

    private final AtomicInteger nextId = new AtomicInteger(1);

    public CalculationRecord calculate(Operation operation, List<BigDecimal> numbers) {
        BigDecimal result = operation.apply(numbers);
        int id = nextId.getAndIncrement();

        CalculationRecord record = new CalculationRecord(id, operation, numbers, result, LocalDateTime.now());
        history.save(id, record);
        return record;
    }

    public boolean hasHistory() {
        return !history.isEmpty();
    }

    public String buildHistoryReport() {
        StringBuffer report = new StringBuffer();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Runnable reportTask = () -> {
            report.append("======= Calculation history =======").append(System.lineSeparator());
            for (CalculationRecord record : history.getAll()) {
                report.append("#").append(record.getId())
                            .append(" [").append(record.getOperation().getLabel()).append("] ")
                                .append(record.getOperands())
                                .append(" = ").append(record.getResult())
                                .append(" (").append(record.getTimestamp().format(formatter)).append(")")
                                .append(System.lineSeparator());
            }
        };

        Thread reportThread = new Thread(reportTask,"report-builder");
        reportThread.start();
        try {
            reportThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return report.toString();
    }
}
