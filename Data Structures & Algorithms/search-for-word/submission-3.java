class Solution {
    public boolean exist(char[][] board, String word) {
        int ROW = board.length;
        int COL = board[0].length;
        boolean[][] visited = new boolean[ROW][COL];

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if(visit(i, j, 0, board, word, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean visit(
        int row, int col, int index, char[][] board, String word, boolean[][] visited
    ) {
        if (!inBound(row, col, board)) return false;
        if (visited[row][col]) return false;
        if (board[row][col] != word.charAt(index)) return false;
        if (index == word.length() -1) return true;

        int[][] directions = {{-1,0}, {1, 0}, {0, -1}, {0, 1}};
        boolean result = false;
        visited[row][col] = true;
        for (int[] direction: directions) {
            result = result || 
            visit(row + direction[0], col + direction[1], index + 1, board, word, visited);
        }
        visited[row][col] = false;
        return result;
    }

    private boolean inBound(int row, int col, char[][] board) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
}
