package challenges.leetcode;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 339. Nested List Weight Sum
 * 
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * 
 * Example 1:
 * 		Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
 * 
 * Example 2:
 * 		Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
 * 
 * @author Hxkandwal
 */
public class NestedListWeightSum extends AbstractCustomTestRunner {

	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementation
	public class NestedInteger {

		// @return true if this NestedInteger holds a single integer, rather than a nested list.
		public boolean isInteger() {
			return false;
		}

		// @return the single integer that this NestedInteger holds, if it holds a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger() {
			return null;
		}

		// @return the nested list that this NestedInteger holds, if it holds a nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList() {
			return null;
		}
	}
	
	// recursive solution
	public int depthSumRecursive(List<NestedInteger> nestedList) {
		return depthSum(nestedList, 1);
	}

	public int depthSum(List<NestedInteger> nestedList, int depth) {
		int sum = 0;
		for (NestedInteger in : nestedList)
			sum += (in.isInteger()) ? depth * in.getInteger() : depthSum(in.getList(), depth + 1);
		return sum;
	}

	// Iterative solution
	public int depthSumIterative(List<NestedInteger> nestedList) {
        int sum = 0;
        Stack<Iterator<NestedInteger>> stack = new Stack<>();
        stack.push (nestedList.iterator());
        
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> itr = stack.peek ();
            if (!itr.hasNext()) stack.pop();
            else {
                NestedInteger in = itr.next ();
                if (in.isInteger()) sum += stack.size() * in.getInteger();
                else stack.push (in.getList().iterator());
            }
        }
        return sum;
    }
	
	public int depthSumIterative2(List<NestedInteger> nestedList) {
		int res = 0;
		Stack<Iterator<NestedInteger>> stk = new Stack<>();
		stk.push(nestedList.iterator());

		while (!stk.isEmpty()) {
			Iterator<NestedInteger> itr = stk.peek();
			while (itr.hasNext()) {
				NestedInteger n = itr.next();
				if (n.isInteger()) res += n.getInteger() * stk.size();
				else { stk.push(n.getList().iterator()); break; }
			}
			if (!stk.peek().hasNext()) stk.pop();
		}
		return res;
	}
 
}
