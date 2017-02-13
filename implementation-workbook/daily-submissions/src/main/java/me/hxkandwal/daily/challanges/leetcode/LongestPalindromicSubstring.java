package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 5. Longest Palindromic Substring
 * 
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * 
 * Example:
 * 		Input: "babad"
 * 		Output: "bab"
 * 
 * 		Note: "aba" is also a valid answer.
 * 
 * Example:
 * 		Input: "cbbd"
 * 		Output: "bb"
 * 	
 * 
 * @author Hxkandwal
 *
 */
public class LongestPalindromicSubstring extends AbstractCustomTestRunner {
	
	private static LongestPalindromicSubstring _instance = new LongestPalindromicSubstring();
			
	private LongestPalindromicSubstring() {}

	// diagonal matching, right to left information storing (not propagating)
	public static String _longestPalindrome(String s) {
		int [][] dp = new int [s.length() + 1][s.length() + 1];
		
		int maxLength = 0;
		String answer = null;
		
		for (int row = 0; row < s.length(); row ++) {
				
			for (int col = row; col >= 0; col --) {
				if (s.charAt(row) == s.charAt(col))
					dp [row + 1][col + 1] = (row == col) ? 1 : (row - col == 1 || dp [row][col + 2] > 0) ? dp [row][col + 2] + 2 : 0;
				
				if (dp [row + 1][col + 1] > maxLength) {
					maxLength = Math.max (maxLength, dp [row + 1][col + 1]);
					answer = s.substring(col, row + 1);
				}
			}
		}
		
		return answer;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("bbbab", "bbb");
		_instance.runTest("cbbd", "bb");
		_instance.runTest("abccba", "abccba");
		_instance.runTest("abcdcba", "abcdcba");
		_instance.runTest("ab", "a");
		_instance.runTest("abcda", "a");
	}
	
	public void runTest(final String s, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}