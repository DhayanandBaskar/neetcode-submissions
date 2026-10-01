class Solution {
    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int islands = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (explore(grid, visited, row, col)) {
                    islands++;
                }
            }
        }

        return islands;
    }

    private boolean explore(
        char[][] grid,
        boolean[][] visited,
        int row,
        int col
    ) {
        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length ||
            grid[row][col] == '0' ||
            visited[row][col]) {
            return false;
        }

        visited[row][col] = true;

        explore(grid, visited, row + 1, col);
        explore(grid, visited, row - 1, col);
        explore(grid, visited, row, col + 1);
        explore(grid, visited, row, col - 1);

        return true;
    }
}