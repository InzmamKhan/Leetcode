class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long MOD = 1_000_000_007L;
        long inv10 = 700_000_005L;

        int[] P = new int[n + 1];
        long[] sumP = new long[n + 1];
        long[] W = new long[n + 1];
        long[] pow10 = new long[n + 1];
        long[] powInv10 = new long[n + 1];

        pow10[0] = 1;
        powInv10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
            powInv10[i] = (powInv10[i - 1] * inv10) % MOD;
        }

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            P[i + 1] = P[i] + (digit > 0 ? 1 : 0);
            sumP[i + 1] = sumP[i] + digit;
            
            long term = 0;
            if (digit > 0) {
                term = (digit * powInv10[P[i + 1]]) % MOD;
            }
            W[i + 1] = (W[i] + term) % MOD;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            long sum = sumP[r + 1] - sumP[l];
            long diffW = (W[r + 1] - W[l] + MOD) % MOD;
            long x = (diffW * pow10[P[r + 1]]) % MOD;

            ans[i] = (int) ((x * (sum % MOD)) % MOD);
        }

        return ans;
    }
}