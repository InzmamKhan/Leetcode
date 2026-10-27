class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '#';
            }
        }

        int r = 0, c = 0;
        grid[r][c] = '.';

        while (r < m - 1 || c < n - 1) {
            if (c < n - 1) {
                c++;
            } else {
                r++;
            }
            grid[r][c] = '.';
        }

        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        return result;
    }
}