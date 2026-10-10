class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int maxVal = 0;
        int[] count = new int[100001];
        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            count[diff]++;
            maxVal = Math.max(maxVal, diff);
        }
        
        for (int i = maxVal; i > 0; i--) {
            if (count[i] > 0) {
                long take = Math.min(k, count[i]);
                count[i] -= take;
                count[i - 1] += take;
                k -= take;
                if (k == 0) {
                    break;
                }
            }
        }
        
        if (k > 0) {
            return 0;
        }
        
        long ans = 0;
        for (int i = 1; i <= maxVal; i++) {
            if (count[i] > 0) {
                ans += (long) count[i] * i * i;
            }
        }
        return ans;
    }
}