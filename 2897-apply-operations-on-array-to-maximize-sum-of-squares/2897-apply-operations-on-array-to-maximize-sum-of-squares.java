class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[32];
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if ((x & (1 << i)) != 0) {
                    bitCounts[i]++;
                }
            }
        }
        
        long[] maxNums = new long[k];
        for (int i = 31; i >= 0; i--) {
            for (int j = 0; j < k; j++) {
                if (bitCounts[i] > 0) {
                    maxNums[j] |= (1L << i);
                    bitCounts[i]--;
                }
            }
        }
        
        long mod = 1_000_000_007;
        long ans = 0;
        for (int i = 0; i < k; i++) {
            ans = (ans + (maxNums[i] % mod) * (maxNums[i] % mod)) % mod;
        }
        return (int) ans;
    }
}