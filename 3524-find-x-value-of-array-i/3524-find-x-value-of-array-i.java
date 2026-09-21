class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] result = new long[k];
        long[] dp = new long[k];

        for (int i = 0; i < n; i++) {
            long[] nextDp = new long[k];
            int val = nums[i] % k;
            
            nextDp[val]++;
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    nextDp[(r * val) % k] += dp[r];
                }
            }
            
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }
            dp = nextDp;
        }

        return result;
    }
}