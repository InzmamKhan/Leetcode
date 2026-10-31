class Solution {
    public int uniquePaths(int[][] grid) {
        int mod = 1000000007;
        int m = grid.length;
        int n = grid[0].length;
        int[] right = new int[n];
        int[] down = new int[n];
        right[0] = 1;
        down[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int a = j > 0 ? right[j - 1] : 0;
                int b = i > 0 ? down[j] : 0;
                int total = (a + b) % mod;
                if (i == m - 1 && j == n - 1) {
                    return total;
                }
                if (grid[i][j] == 0) {
                    right[j] = total;
                    down[j] = total;
                } else {
                    right[j] = b;
                    down[j] = a;
                }
            }
        }
        return 0;
    }
}