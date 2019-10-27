import unittest

from StringCalculator import StringCalculator, NegativeException

class StringCalculatorTest(unittest.TestCase):
    def setUp(self):
        self.sc = StringCalculator()
    
    def test_empty(self):
        self.assertEqual(0, self.sc.Add(""))

    def test_three(self):
        self.assertEqual(8, self.sc.Add("1,2,5"))

    def test_threeNL(self):
        self.assertEqual(6, self.sc.Add("1\n,2,3"))
        self.assertEqual(7, self.sc.Add("1,\n2,4"))

    def test_delim(self):
        self.assertEqual(8, self.sc.Add("//;\n1;3;4"))
        self.assertEqual(6, self.sc.Add("//$\n1$2$3"))
        self.assertEqual(13, self.sc.Add("//@\n2@3@8"))

    def test_noNegative(self):
        with self.assertRaises(NegativeException) as neContext:
            self.sc.Add("1,-2,3,-4")
        ne = neContext.exception
        self.assertTrue(0 < ne.message.find("-2"))
        self.assertTrue(0 < ne.message.find("-4"))

    def test_tooLarge(self):
        self.assertEqual(1002, self.sc.Add("2,1000"))
        self.assertEqual(2, self.sc.Add("2,1001"))


if __name__ == '__main__':
    unittest.main()
