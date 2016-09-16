package me.hxkandwal.daily.challanges.hackerrank;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static com.google.common.truth.Truth.assertThat;

/**
 * The Maximum Subarray
 *
 * Given an array A of n elements, find the maximum possible sum of a :
 * a) Contiguous subarray
 * b) Non-contiguous (not necessarily contiguous) subarray.
 *
 * Empty subarrays/subsequences should not be considered.
 *
 * Example :
 * a)   4
 *      1 2 3 4
 *
 * Output : 10 10
 *          The max sum for both contiguous and non-contiguous elements is the sum of ALL the elements (as they are all positive).
 *
 * b)   6
 *      2 -1 2 3 4 -5
 *
 * Output : 10 11
 *          [2 -1 2 3 4] --> This forms the contiguous sub-array with the maximum sum.
 *          For the max sum of a not-necessarily-contiguous group of elements, simply add all the positive elements.
 *
 * Created by Hxkandwal
 */
public class MaximumSubarray extends AbstractCustomTestRunner {

    private static MaximumSubarray _instance = new MaximumSubarray();

    private MaximumSubarray() {}

    public static long[] _getMaximum(int[] input) {
        long maximumNonContiguousSum = 0;
        for (int idx = 0; idx < input.length; idx ++)
            if (input[idx] > 0)
                maximumNonContiguousSum += input [idx];

        long maxSum, runningSum, start, end, j;
        maxSum = runningSum = input[0];
        start = end = j = 0;

        for (int index = 1; index < input.length; index ++) {
            int currentValue = input [index];

            if (runningSum + currentValue >= currentValue) {
                runningSum += currentValue;
            } else {
                runningSum = currentValue;
                j = index;
            }

            if (maxSum <= runningSum) {
                maxSum = runningSum;
                start = j;
                end = index;
            }
        }

        return new long[] {maxSum, maximumNonContiguousSum};
    }
    
    // driver method
    public static void main(String[] args) throws FileNotFoundException {
        _instance.runTest(new int[] { 1, 2, 3, 4 }, new long[] { 10, 10 });
        _instance.runTest(new int[] { 2, -1, 2, 3, 4, -5 }, new long[] { 10, 11 });

        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-1.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-2.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-3.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-4.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-5.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-6.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-7.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-8.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-9.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MaximumSubarray-Big-10.txt");
    }

    private static void testComplex(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + filename));

        int[] input = new int[sc.nextInt()];
        for (int idx = 0; idx < input.length; idx ++)
            input [idx] = sc.nextInt();

        _instance.runTest(input, new long[] { sc.nextLong(), sc.nextLong() });

        sc.close();
    }

    public void runTest(final int[] input, final long[] expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((long[]) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}