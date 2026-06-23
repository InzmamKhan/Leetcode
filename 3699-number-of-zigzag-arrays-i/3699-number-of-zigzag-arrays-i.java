class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int MOD = 1000000007;
        int K = r - l + 1;
        
        if (n == 1) {
            return K;
        }
        
        long[][] dp = new long[K][2];
        
        for (int v = 0; v < K; v++) {
            dp[v][0] = v;         
            dp[v][1] = (K - 1 - v); 
        }
        
        for (int i = 3; i <= n; i++) {
            long[][] nextDp = new long[K][2];
            
            long[] prefDown = new long[K + 1];
            long[] prefUp = new long[K + 1];
            
            for (int v = 0; v < K; v++) {
                prefDown[v + 1] = (prefDown[v] + dp[v][1]) % MOD;
                prefUp[v + 1] = (prefUp[v] + dp[v][0]) % MOD;
            }
            
            for (int v = 0; v < K; v++) {
                nextDp[v][0] = prefDown[v];
                long sumUp = (prefUp[K] - prefUp[v + 1] + MOD) % MOD;
                nextDp[v][1] = sumUp;
            }
            
            dp = nextDp;
        }
        
        long total = 0;
        for (int v = 0; v < K; v++) {
            total = (total + dp[v][0] + dp[v][1]) % MOD;
        }
        
        return (int) total;
    }
}