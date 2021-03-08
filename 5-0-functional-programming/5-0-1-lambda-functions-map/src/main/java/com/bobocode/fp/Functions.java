package com.bobocode.fp;

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
        intFunctionMap.addFunction("sgn", val -> (int) Math.signum(val));
        intFunctionMap.addFunction("increment", val -> ++val);
        intFunctionMap.addFunction("decrement", val -> --val);
        intFunctionMap.addFunction("square", val -> val * val);

        return intFunctionMap;
    }
}
