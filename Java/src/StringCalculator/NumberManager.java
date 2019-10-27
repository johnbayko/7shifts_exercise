package StringCalculator;

import java.util.List;
import java.util.LinkedList;
import java.util.StringJoiner;

public class NumberManager {
  private final List<Integer> numberList;

  public NumberManager(final String numbers)
    throws NegativeException
  {
    numberList = new LinkedList<Integer>();

    final NumberParser np = new NumberParser(numbers);

    final List<Integer> negativeList = new LinkedList<>();

    for (Integer newNumber : np.getNumbers()) {
      if (newNumber >= 0) {
        if (newNumber <= 1000) {
          numberList.add(newNumber);
        }
      } else {
        negativeList.add(newNumber);
      }
    }
    if (0 < negativeList.size()) {
      final StringJoiner sj = new StringJoiner(", ");
      for (Integer negativeNum: negativeList) {
        sj.add(negativeNum.toString());
      }
      throw new NegativeException("Negetives not allowed: " + sj.toString());
    }
  }

  public List<Integer> getNumberList() {
    return numberList;
  }
}
