package ch07.calculator;

import ch07.aspect.Atarget;

public class RecCalculator implements Calculator{
    @Override
    @Atarget
    public long factorial(long num) {
        if (num == 0) {
            return 1;
        } else {
            return num * factorial(num - 1);
        }
    }
}
