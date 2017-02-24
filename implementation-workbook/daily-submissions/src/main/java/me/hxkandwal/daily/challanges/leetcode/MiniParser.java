package me.hxkandwal.daily.challanges.leetcode;

import java.util.List;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 385. Mini Parser
 * 
 * Given a nested list of integers represented as a string, implement a parser to deserialize it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * 
 * Note: You may assume that the string is well-formed:
 * 		String is non-empty.
 * 		String does not contain white spaces.
 * 		String contains only digits 0-9, [, - ,, ].
 * 
 * Example 1:
 * 		Given s = "324",
 * 
 * 		You should return a NestedInteger object which contains a single integer 324.
 * 
 * Example 2:
 * 		Given s = "[123,[456,[789]]]",
 * 
 * 		Return a NestedInteger object containing a nested list with 2 elements:
 * 
 * 1. An integer containing value 123.
 * 2. A nested list containing two elements:
 * 		i.  An integer containing value 456.
 * 		ii. A nested list with one element:
 * 	 		a. An integer containing value 789.
 * 
 * @author Hxkandwal
 */
public class MiniParser extends AbstractCustomTestRunner {

	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementation
	public class NestedInteger {
		// Constructor initializes an empty nested list.
		public NestedInteger() {}
		
	 	// Constructor initializes a single integer.
		public NestedInteger(int value) {}
		
	 	// @return true if this NestedInteger holds a single integer, rather than a nested list.
		public boolean isInteger() { return false; }

		// @return the single integer that this NestedInteger holds, if it holds a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger() { return null; }
		
		// Set this NestedInteger to hold a single integer.
		public void setInteger(int value) {}
		
		// Set this NestedInteger to hold a nested list and adds a nested integer to it.
		public void add(NestedInteger ni) {} 
		
		// @return the nested list that this NestedInteger holds, if it holds a nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList() { return null; }
	 }
	 
	public NestedInteger deserialize(String s) {
        Stack<NestedInteger> stk = new Stack<>();
        Integer num = null;
        int sign = 1;
        for (int idx = 0; idx < s.length(); idx ++) {
            char ch = s.charAt(idx);
            
            if (Character.isDigit(ch)) num = 10 * (num == null ? 0 : num) + (ch - '0');
            else if (ch == '-') sign = -1;
            else {
                if (ch == ',' && num != null) {
                    stk.peek().add (new NestedInteger (sign * num));
                    num = null; sign = 1;
                } 
                else if (ch == '[') stk.push (new NestedInteger());
                else if (ch == ']') {
                    if (num != null) { 
                        stk.peek().add (new NestedInteger (sign * num)); 
                        num = null; 
                        sign = 1;
                    }
                    if (stk.size() > 1) {
                        NestedInteger poppedList = stk.pop();
                        stk.peek().add (poppedList);
                    }
                }
            }
        }
        return (stk.isEmpty()) ? new NestedInteger (sign * num) : stk.pop();
    }
	
}
