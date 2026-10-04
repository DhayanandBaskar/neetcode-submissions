class Solution {
    public int numIslands(char[][] grid) {
        int island = 0;
        int ROW = grid.length;
        int COL = grid[0].length;
        boolean[][] visited = new boolean[ROW][COL];

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    island++;
                    explore(i, j, grid, visited);
                }
            }
        }

        return island;
    }

    private void explore(int row, int col, char[][] grid, boolean[][] visited) {
        if (!inBound(row, col, grid)) return;
        if (visited[row][col]) return;
        if (grid[row][col] == '0') return;

        visited[row][col] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int[] dir: directions) {
            explore(row + dir[0], col + dir[1], grid, visited);
        }
    }

    private boolean inBound(int row, int col, char[][] grid) {
        return row >=0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
}