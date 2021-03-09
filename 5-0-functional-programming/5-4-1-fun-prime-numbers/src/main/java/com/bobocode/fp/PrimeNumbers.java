package com.bobocode.fp;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * {@link PrimeNumbers} provides an API to work with prime numbers. It is using a stream of prime numbers.
 * <p>
 * See {@link OOSumOfPrimes} for a reference
 */
public class PrimeNumbers {
    private PrimeNumbers() {
    }

    /**
     * Calculates the sum on first n prime numbers.
     * E.g. if n = 5, the result should be 1 + 2 + 3 + 5 + 7 = 18
     *
     * @param n the number of first prime numbers
     * @return the sum of n prime numbers
     */
    public static int sum(int n) {
        // todo: create an infinite stream of ints, then filter prime numbs
        return Stream.iterate(1, x -> x + 1)
                .filter(currentLimitNumber ->
                        IntStream.range(2, currentLimitNumber).noneMatch(number -> currentLimitNumber % number == 0))
                .limit(n)
                .reduce(0, Integer::sum);
        // Another implementation
        /*return Stream.iterate(1, x -> x + 1)
                .filter(currentLimitNumber ->
                        IntStream.range(2, currentLimitNumber).noneMatch(number -> currentLimitNumber % number == 0))
                .limit(n)
                .mapToInt(Integer::intValue)
                .sum();*/
    }

    /**
     * Collects n first prime numbers.
     *
     * @return a list of collected prime numbers
     */
    public static List<Integer> collect(int n) {
        // todo: reuse the logic of prime numbers stream and collect them
        return Stream.iterate(1, x -> x + 1)
                .filter(currentLimitNumber ->
                        IntStream.range(2, currentLimitNumber).noneMatch(number -> currentLimitNumber % number == 0))
                .limit(n)
                .collect(Collectors.toList());
    }

    /**
     * Find a prime number by index and then applies a provided consumer passing found prime number
     *
     * @param idx      the position of a prime number (index)
     * @param consumer a logic that should be applied to the found prime number
     */
    public static void processByIndex(int idx, IntConsumer consumer) {
        // todo: reuse the logic of prime numbers stream then process the last one
        PrimeNumbers.collect(idx).stream().skip(idx - 1).findFirst().ifPresent(consumer::accept);
    }
}
