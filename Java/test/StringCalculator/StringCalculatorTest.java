package StringCalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringCalculatorTest {
  @Test
  void addTest() {
    try {
      assertEquals(0, StringCalculator.Add(""));
      assertEquals(8, StringCalculator.Add("1,2,5"));
    } catch (NegativeException ex) {
    }
  }

  @Test
  void newLineTest() {
    try {
      assertEquals(6, StringCalculator.Add("1\n,2,3"));
      assertEquals(7, StringCalculator.Add("1,\n2,4"));
    } catch (NegativeException ex) {
    }
  }

  @Test
  void customDelimTest() {
    try {
      assertEquals(8, StringCalculator.Add("//;\n1;3;4"));
      assertEquals(6, StringCalculator.Add("//$\n1$2$3"));
      assertEquals(13, StringCalculator.Add("//@\n2@3@8"));
    } catch (NegativeException ex) {
    }
  }

  @Test
  void negativeTest() {
    NegativeException ex =
      assertThrows(
        NegativeException.class,
        () -> StringCalculator.Add("1,-2,3,-4")
      );
    assertTrue(ex.getMessage().contains("-2"));
    assertTrue(ex.getMessage().contains("-4"));
  }

  @Test
  void tooLargeTest() {
    try {
      assertEquals(1002, StringCalculator.Add("2,1000"));
      assertEquals(2, StringCalculator.Add("2,1001"));
    } catch (NegativeException ex) {
    }
  }

  @Test
  void longDelimTest() {
    try {
      assertEquals(6, StringCalculator.Add("//***\n1***2***3"));
    } catch (NegativeException ex) {
    }
  }

  @Test
  void multiDelimTest() {
    try {
      assertEquals(6, StringCalculator.Add("//$,@\n1$2@3"));
    } catch (NegativeException ex) {
    }
  }
}
