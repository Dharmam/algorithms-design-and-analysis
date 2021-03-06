package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 79. Word Search
 * 
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those 
 * horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * 
 * For example,
 * 		Given board =
 * 			[
 * 				['A','B','C','E'],
 * 				['S','F','C','S'],
 * 				['A','D','E','E']
 * 			]
 * 
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * 
 * @author Hxkandwal
 */
public class WordSearch extends AbstractCustomTestRunner {
	
	private static WordSearch _instance = new WordSearch();
	
    public boolean exist(char[][] board, String word) {
        if (board.length == 0) return word.length() == 0;
        if (word.length() == 0) return true;
        for (int row = 0; row < board.length; row ++)
            for (int col = 0; col < board [0].length; col ++)
                if (word.charAt (0) == board [row][col] && find (board, word, row, col, 0)) 
                    return true;
        return false;        
    }
    
    private boolean find (char[][] board, String word, int row, int col, int idx) {
        if (idx >= word.length()) return true;
        if (row >= board.length || row < 0 || col >= board [0].length || col < 0) return false;
        char ch = word.charAt (idx);
        if (board [row][col] != ch) return false;
        
        board [row][col] = '*';
        boolean res =   find (board, word, row, col + 1, idx + 1) 
                    ||  find (board, word, row + 1, col, idx + 1)
                    ||  find (board, word, row - 1, col, idx + 1) 
                    ||  find (board, word, row, col - 1, idx + 1);
        board [row][col] = ch;
        return res;
    }
    
    // driver method
 	public static void main(String[] args) {
 		_instance.runTest(new char[][] { "ABCE".toCharArray(), "SFCS".toCharArray(), "ADEE".toCharArray() }, "ABCCED", true);
 		_instance.runTest(new char[][] { "ABCE".toCharArray(), "SFCS".toCharArray(), "ADEE".toCharArray() }, "SEE", true);
 		_instance.runTest(new char[][] { "ABCE".toCharArray(), "SFCS".toCharArray(), "ADEE".toCharArray() }, "ABCB", false);
 	}

 	public void runTest(char[][] board, String word, final boolean expectedOutput) {
 		List<Object> answers = runAll(getClass(), new Object[] { board, word });

 		for (Object answer : answers)
 			assertThat((Boolean) answer).isEqualTo(expectedOutput);

 		System.out.println("ok!");
 	}    
}
