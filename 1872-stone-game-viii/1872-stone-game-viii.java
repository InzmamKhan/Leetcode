class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i];
        }
        
        int maxDiff = prefixSum[n - 1];
        for (int i = n - 2; i >= 1; i--) {
            maxDiff = Math.max(maxDiff, prefixSum[i] - maxDiff);
        }
        
        return maxDiff;
    }
}