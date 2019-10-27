package StringCalculator;

public class StringCalculator {
  public static int Add(String numbers)
    throws NegativeException
  {
    final NumberManager nm = new NumberManager(numbers);

    final int sum =
        nm.getNumberList()
          .stream()
          .mapToInt(i -> i)
          .sum();

    return sum;
  }
}
