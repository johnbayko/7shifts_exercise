package StringCalculator;

public class NumberString {
  public final String delimString;
  public final String numbersString;

  public NumberString(String numbers) {
    final int delimStart = getDelimStart(numbers);
    final int delimEnd = getDelimEnd(numbers);

    delimString = getDelimString(numbers, delimStart, delimEnd);
    numbersString = getNumbersString(numbers, delimStart, delimEnd);
  }

  private int getDelimStart(String numbers) {
    return numbers.startsWith("//") ? 2 : 0;
  }

  private int getDelimEnd(String numbers) {
    return numbers.startsWith("//") ? numbers.indexOf('\n') : 0;
  }

  private String getDelimString(
      final String numbers,
      final int delimStart,
      final int delimEnd
  ) {
    return (delimEnd > delimStart) 
      ? numbers.substring(delimStart, delimEnd)
      : null;
  }

  private String getNumbersString(
      final String numbers,
      final int delimStart,
      final int delimEnd
  ) {
    return (delimEnd > delimStart)
        ? numbers.substring(delimEnd)
        : numbers;
  }
}
