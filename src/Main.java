import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Map<Integer, Operation> MENU = Map.of(
            1, Operation.SUM,
            2, Operation.SUBTRACT
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        boolean keepGoing = true;

        System.out.println("======= Simple Calculator (Sum & Subtraction) =======");

        while (keepGoing) {
            Operation operation = MENU.get(readOperationChoice(scanner));

            System.out.println("Enter All numbers separated by commas (e.g. 10, 3, 2):");
            String rawInput = scanner.nextLine();

            try {
                List<BigDecimal> numbers = NumberParser.parse(rawInput);
                CalculationRecord record = calculator.calculate(operation, numbers);
                System.out.println(operation.getLabel() + " result: " + record.getResult());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("Do another calculation? (y/n)");
            String answer = scanner.nextLine().trim();
            keepGoing = answer.equalsIgnoreCase("y");
        }

        System.out.println();
        if (calculator.hasHistory()) {
            System.out.println(calculator.buildHistoryReport());
        } else {
            System.out.println("No Successful calculations were made.");
        }

        System.out.println("Goodbye, Sandro!");
        scanner.close();
    }

    private static Integer readOperationChoice(Scanner scanner) {
        Integer choice = null;
        while (choice == null) {
            System.out.println("Choose an operation: 1) Sum  2) Subtraction");
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (MENU.containsKey(value)) {
                    choice = value;
                } else {
                    System.out.println("Please type 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please type a valid number (1 or 2).");
            }
        }
        return choice;
    }
}