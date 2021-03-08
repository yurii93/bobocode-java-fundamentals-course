package com.bobocode.fp;

import static java.lang.Math.abs;

public class Functions {
    /**
     * A static factory method that creates an integer function map with basic functions:
     * - abs (absolute value)
     * - sgn (signum function)
     * - increment
     * - decrement
     * - square
     *
     * @return an instance of {@link FunctionMap} that contains all listed functions
     */
    public static FunctionMap<Integer, Integer> intFunctionMap() {
        FunctionMap<Integer, Integer> intFunctionMap = new FunctionMap<>();

        // todo: add simple functions to the function map (abs, sgn, increment, decrement, square)
        intFunctionMap.addFunction("abs", Math::abs);
        intFunctionMap.addFunction("sgn", n -> (n != 0) ? n / abs(n) : 0);
        intFunctionMap.addFunction("increment", n -> n + 1);
        intFunctionMap.addFunction("decrement", n -> n - 1);
        intFunctionMap.addFunction("square", n -> n * n);

        return intFunctionMap;
    }
}
