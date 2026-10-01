class Solution {
    record Cell(int row, int col) {}

    public int orangesRotting(int[][] grid) {
        Queue<Cell> queue = new ArrayDeque<>();
        int fresh = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 2) {
                    queue.add(new Cell(row, col));
                } else if (grid[row][col] == 1) {
                    fresh++;
                }
            }
        }

        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int minutes = 0;

        while (fresh > 0 && !queue.isEmpty()) {
            int rottenThisMinute = queue.size();

            for (int i = 0; i < rottenThisMinute; i++) {
                Cell cell = queue.remove();

                for (int[] direction : directions) {
                    int row = cell.row() + direction[0];
                    int col = cell.col() + direction[1];

                    if (isInBounds(row, col, grid) && grid[row][col] == 1) {
                        grid[row][col] = 2;
                        fresh--;
                        queue.add(new Cell(row, col));
                    }
                }
            }

            minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }

    private boolean isInBounds(int row, int col, int[][] grid) {
        return row >= 0 && row < grid.length &&
               col >= 0 && col < grid[0].length;
    }
}