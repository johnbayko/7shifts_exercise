package StringCalculator;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class NumberSplitter {
  private final List<Pattern> delimPatternList;
  private final Pattern nlPattern = Pattern.compile("\n");

  public NumberSplitter(String delimiters) {
    final List<String> delimStringList;

    if (null != delimiters) {
      delimStringList = Arrays.asList(delimiters.split(","));
    } else {
      // No delimiter (default ",").
      delimStringList = new LinkedList<>();
      delimStringList.add(",");
    }

    delimPatternList = makeDelimPatterns(delimStringList);
  }

  private List<Pattern> makeDelimPatterns(final List<String> delimStringList) {
    final List<Pattern> delimPatternList = new LinkedList<Pattern>();

    // Compile all delimiter strings.
    for (String delimString : delimStringList) {
        delimPatternList.add(Pattern.compile(delimString, Pattern.LITERAL));
    }
    return delimPatternList;
  }


  public List<String> getNumberStrings(final String numbers) {
    final List<String> numberStrings = new LinkedList<String>();

    for (String numberString : split(delimPatternList, numbers)) {
      final String cleanNumber = clean(numberString);
      numberStrings.add(cleanNumber);
    }

    return numberStrings;
  }

  private String clean(final String numberString) {
    return nlPattern
          .matcher(numberString)
          .replaceAll("");
  }

  private List<String> split(
    final List<Pattern> delimPatternList,
    final String numbers
  ) {
    List<String> numberStringList = new LinkedList<String>();
    numberStringList.add(numbers);

    // Apply each pattern to substring list to split further.
    for (Pattern delimPattern : delimPatternList) {
      numberStringList = splitList(delimPattern, numberStringList);
    }
    return numberStringList;
  }

  private List<String> splitList(
      final Pattern delimPattern,
      final List<String> numberStringList
  ) {
      final List<String> newNumberStringList = new LinkedList<String>();

      for (String numberString : numberStringList) {
        // Add each substring split from previous string list to new list.
        for (String newNumberString : delimPattern.split(numberString)) {
          newNumberStringList.add(newNumberString);
        }
      }
      return newNumberStringList;
  }
}

