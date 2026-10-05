# Calculator — Sum & Subtraction (Java)

A Java console calculator built as an exercise from the DIO bootcamp. It performs **sum** and **subtraction** on a list of numbers the user types all at once, separated by commas, and keeps a session history of every calculation made.

## What it does

- Asks the user to choose an operation: **1) Sum** or **2) Subtraction**.
- Asks for all the numbers in a single line, comma-separated (e.g. `10, 3, 2`).
- **Sum**: adds every number together.
- **Subtraction**: subtracts the sum of all the remaining numbers from the first one (e.g. `10, 3, 2` → `10 - (3 + 2) = 5`).
- Rejects invalid input (text that is not a number) with a clear error message, without crashing.
- Lets the user repeat the calculation as many times as they want.
- At the end, prints a full report of every successful calculation made in that session, with date and time.

## Concepts practiced

| Feature | Where it is used |
|---|---|
| **Enums** | `Operation` (`SUM`, `SUBTRACT`), each one implementing its own calculation |
| **Generics** | `HistoryRepository<K, V>`, a reusable storage class |
| **Optional** | Safe number parsing in `NumberParser`, and lookups in `HistoryRepository` |
| **Streams API** | `reduce()` used inside `Operation` to sum/subtract the numbers |
| **BigDecimal** | All arithmetic, to avoid `double` rounding errors |
| **Map** | The operation menu in `Main` (`1` → Sum, `2` → Subtraction) |
| **Wrapper classes** | `Integer` (menu keys, `parseInt`), `AtomicInteger` (id counter) |
| **String** | `split`, `trim`, `equalsIgnoreCase` |
| **StringBuilder** | Building the invalid-input error message in `NumberParser` |
| **StringBuffer** | Building the history report (thread-safe, written from another thread) |
| **LocalDateTime** | Timestamp of each calculation |
| **Thread / Runnable** | The history report is built on a background thread and joined before printing |

`Date`, `Calendar`, `OffsetDateTime`, `LocalDate` and `LocalTime` were intentionally left out: `Date`/`Calendar` are the older, pre-Java 8 API already replaced by `LocalDateTime`, and the other types were not needed since this is a single local session with no time zone involved.

## Project structure

```
CalculatorSumSubtraction/
├── src/
│   ├── Operation.java           # enum: SUM and SUBTRACT, each one calculates itself
│   ├── CalculationRecord.java   # immutable record of one calculation
│   ├── HistoryRepository.java   # generic storage used to keep the session history
│   ├── NumberParser.java        # turns "10, 3, 2" into a validated List<BigDecimal>
│   ├── Calculator.java          # runs the calculation and builds the history report
│   └── Main.java                # console program (menu + input loop)
└── .gitignore
```

## How to run

**Requirements:** JDK 8 or higher.

### Using IntelliJ IDEA

1. Open the project folder in IntelliJ.
2. Open `src/Main.java`.
3. Click the green run button next to `main`.

### Using the command line

From the project root folder:

```
javac -d out src/Operation.java src/CalculationRecord.java src/HistoryRepository.java src/NumberParser.java src/Calculator.java src/Main.java
java -cp out Main
```

## Example session

```
=== Simple Calculator (Sum & Subtraction) ===
Choose an operation: 1) Sum  2) Subtraction
1
Enter ALL numbers separated by commas (e.g. 10, 3, 2):
10, 3, 2
Sum result: 15
Do another calculation? (y/n)
y
Choose an operation: 1) Sum  2) Subtraction
2
Enter ALL numbers separated by commas (e.g. 10, 3, 2):
10, 3, 2
Subtraction result: 5
Do another calculation? (y/n)
n

=== Calculation history =======
#1 [Sum] [10, 3, 2] = 15 (29/09/2026 21:35:36)
#2 [Subtraction] [10, 3, 2] = 5 (29/09/2026 21:35:36)

Goodbye, Sandro!
```

## Author

Sandro ([@Sandro-ia](https://github.com/Sandro-ia))
