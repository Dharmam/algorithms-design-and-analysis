package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 494. Target Sum
 * 
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. 
 * For each integer, you should choose one from + and - as its new symbol.
 * 
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * 
 * Example 1:	
 * 		Input: nums is [1, 1, 1, 1, 1], S is 3.
 * 		Output: 5
 * 		Explanation: 
 * 
 * 			-1+1+1+1+1 = 3
 * 			+1-1+1+1+1 = 3
 * 			+1+1-1+1+1 = 3
 * 			+1+1+1-1+1 = 3
 * 			+1+1+1+1-1 = 3
 * 			
 * 	There are 5 ways to assign symbols to make the sum of nums be target 3.
 * 
 * @author Hxkandwal45
 */
public class TargetSum extends AbstractCustomTestRunner {
	
	private static TargetSum _instance = new TargetSum();

	/**
	 * https://discuss.leetcode.com/topic/76243/java-15-ms-c-3-ms-o-ns-iterative-dp-solution-using-subset-sum-with-explanation
	 * 
	 *       		            sum(P) - sum(N) = target
	 *        sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
	 *        		  				 2 * sum(P) = target + sum(nums)
	 *        
	 * So the original problem has been converted to a subset sum problem as follows:
	 * 			Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
	 * 
	 */
	public int _findTargetSumWaysDP(int[] nums, int s) {
		int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1); 
    }   

    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1]; 
        dp[0] = 1;
        
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n]; 
        return dp[s];
    } 
	
	public int _findTargetSumWays(int[] nums, int S) {
        return count (nums, "", 0, 0, S);
    }
    
    private int count (int [] nums, String s, int index, int total, int S) {
        if (index >= nums.length) { System.out.println(s); return (total == S) ? 1 : 0; }
        total += nums [index];
        int count = count (nums, s + "+" + nums [index], index + 1, total, S);

        total -= nums [index]; total -= nums [index];
        count += count (nums, s + "-" + nums [index], index + 1, total, S);
        return count;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 1, 1, 1, 1 }, 3, 5);
	}

	public void runTest(final int[] nums, final int S, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, S });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}    
}
