import java.util.Arrays;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        int[][] dp = new int[maxVal + 1][maxVal + 1];
        
        dp[0][0] = 1;

        for (int x : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;

                    long currentWays = dp[g1][g2];

                    nextDp[g1][g2] = (int) ((nextDp[g1][g2] + currentWays) % MOD);

                    int n1 = (g1 == 0) ? x : gcd(g1, x);
                    nextDp[n1][g2] = (int) ((nextDp[n1][g2] + currentWays) % MOD);

                    int n2 = (g2 == 0) ? x : gcd(g2, x);
                    nextDp[g1][n2] = (int) ((nextDp[g1][n2] + currentWays) % MOD);
                }
            }
            dp = nextDp;
        }

        long totalPairs = 0;
        for (int g = 1; g <= maxVal; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }

        return (int) totalPairs;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}