class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int[] last = new int[26];
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int ch = s.charAt(i - 1) - 'a';
            dp[i] = (2 * dp[i - 1]) % MOD;

            if (last[ch] > 0) {
                dp[i] = (dp[i] - dp[last[ch] - 1] + MOD) % MOD;
            }

            last[ch] = i;
        }

        return (dp[n] - 1 + MOD) % MOD;
    }
}