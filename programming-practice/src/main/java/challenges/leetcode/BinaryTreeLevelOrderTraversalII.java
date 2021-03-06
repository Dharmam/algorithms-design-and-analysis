package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import challenges.AbstractCustomTestRunner;

/**
 * 107. Binary Tree Level Order Traversal II
 * 
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * 
 * For example:
 * 		Given binary tree [3,9,20,null,null,15,7],
 * 
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 *   
 * return its bottom-up level order traversal as:
 * 
 * [ [15,7], [9,20], [3] ]
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class BinaryTreeLevelOrderTraversalII extends AbstractCustomTestRunner {
	
	private static BinaryTreeLevelOrderTraversalII _instance = new BinaryTreeLevelOrderTraversalII();
	
	public static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 
		 public TreeNode(int x) { val = x; }
	}
	
	// idea : store result in the collector while coming up.  (DFS while doing BFS)
	public static List<List<Integer>> _levelOrderBottom(TreeNode root) {
		LinkedList<List<Integer>> ans = new LinkedList<>();
        if (root == null) return ans;
        
        Queue <TreeNode> queue = new LinkedList<>();
        queue.offer (root);
        while (!queue.isEmpty ()) {
            int size = queue.size ();
            List<Integer> level = new ArrayList <>();
            while (size -- > 0) {
                TreeNode node = queue.poll ();
                level.add (node.val);
                if (node.left != null) queue.offer (node.left);
                if (node.right != null) queue.offer (node.right);
            }
            ans.addFirst (level);
        }
        return ans;
	}
	
	// driver method
	public static void main(String[] args) {
		TreeNode node = new TreeNode(3);
		node.left = new TreeNode(9);
		
		TreeNode right = new TreeNode(20);
		right.left = new TreeNode(15);
		right.right = new TreeNode(7);
				
		node.right = right;
		
		_instance.runTest(node, new ArrayList() {{ add(new ArrayList() {{ add(15); add(7); }}); add(new ArrayList() {{ add(9); add(20); }}); add(new ArrayList() {{ add(3); }}); }});
	}

	public void runTest(final TreeNode node, final List<List<Integer>> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { node });

		for (Object answer : answers)
			assertThat((List<List<Integer>>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
