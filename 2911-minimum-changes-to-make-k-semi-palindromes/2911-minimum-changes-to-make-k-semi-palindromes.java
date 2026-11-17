class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];
        int INF = 1000000000;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int L = j - i + 1;
                int minCost = INF;
                for (int d = 1; d < L; d++) {
                    if (L % d == 0) {
                        int cur = 0;
                        for (int g = 0; g < d; g++) {
                            int leftIdx = i + g;
                            int rightIdx = i + g + (L / d - 1) * d;
                            while (leftIdx < rightIdx) {
                                if (s.charAt(leftIdx) != s.charAt(rightIdx)) {
                                    cur++;
                                }
                                leftIdx += d;
                                rightIdx -= d;
                            }
                        }
                        minCost = Math.min(minCost, cur);
                    }
                }
                cost[i][j] = minCost;
            }
        }

        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = INF;
            }
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int rem = 1; rem <= Math.min(i, k); rem++) {
                for (int j = rem - 1; j < i; j++) {
                    if (dp[j][rem - 1] != INF && cost[j][i - 1] != INF) {
                        dp[i][rem] = Math.min(dp[i][rem], dp[j][rem - 1] + cost[j][i - 1]);
                    }
                }
            }
        }

        return dp[n][k];
    }
}