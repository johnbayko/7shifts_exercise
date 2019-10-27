package StringCalculator;

import java.util.List;
import java.util.LinkedList;

public class NumberParser {
  private final List<Integer> numberList = new LinkedList<>();;

  public NumberParser(String numbers) {
    final NumberString ns = new NumberString(numbers);

    final NumberSplitter splitter = new NumberSplitter(ns.delimString);

    for (String numberString : splitter.getNumberStrings(ns.numbersString)) {
      try {
        numberList.add(Integer.parseInt(numberString));
      } catch (NumberFormatException e) {
        ; // Ignore.
      }
    }
  }

  public List<Integer> getNumbers() {
    return numberList;
  }
}

