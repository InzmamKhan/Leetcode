import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;
        int[][] maxScore = new int[n][n];
        int[][] paths = new int[n][n];

        paths[n - 1][n - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == n - 1 && j == n - 1) {
                    continue;
                }
                if (board.get(i).charAt(j) == 'X') {
                    continue;
                }

                int maxS = -1;
                int pathCount = 0;

                int[][] dirs = {{i + 1, j}, {i, j + 1}, {i + 1, j + 1}};
                for (int[] dir : dirs) {
                    int r = dir[0], c = dir[1];
                    if (r < n && c < n && paths[r][c] > 0) {
                        if (maxScore[r][c] > maxS) {
                            maxS = maxScore[r][c];
                            pathCount = paths[r][c];
                        } else if (maxScore[r][c] == maxS) {
                            pathCount = (pathCount + paths[r][c]) % MOD;
                        }
                    }
                }

                if (maxS != -1) {
                    char ch = board.get(i).charAt(j);
                    int currentScore = (ch == 'E') ? 0 : (ch - '0');
                    maxScore[i][j] = maxS + currentScore;
                    paths[i][j] = pathCount;
                }
            }
        }

        return new int[]{maxScore[0][0], paths[0][0]};
    }
}