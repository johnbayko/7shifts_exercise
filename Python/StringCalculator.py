class NegativeException(Exception):
    pass

class StringCalculator:
    def __init__(self):
        self.MAX_INT = 1000
    
    def Add(self, numbers):
        delim = ","  # default
        if "//" == numbers[0:2]:
            delimStart = 2
            delimEnd = numbers.find("\n")
            delim = numbers[delimStart:delimEnd]
            numberString = numbers[delimEnd:]
        else:
            # No delimiter specifier
            numberString = numbers

        numberStringList = numberString.split(delim)
        if numberStringList[0] == "":
            # There weren't any numbers
            return 0

        negativeList = []
        sum = 0
        for ns in numberStringList:
            # Skip things like "" and "1,,2"
            if ns != "":
                n = int(ns)
                if n <= self.MAX_INT:
                    # No negatives
                    if n >= 0:
                        sum += int(ns)
                    else:
                        negativeList += [ns]

        if 0 == len(negativeList):
            return sum
        else:
            neMsg = "Negative: " + ", ".join(negativeList)
            raise NegativeException(neMsg)
