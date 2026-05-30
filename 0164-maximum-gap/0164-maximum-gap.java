import java.util.Arrays;

class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        for (int num : nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }

        if (minVal == maxVal) {
            return 0;
        }

        int n = nums.length;
        int bucketSize = Math.max(1, (maxVal - minVal) / (n - 1));
        int bucketCount = ((maxVal - minVal) / bucketSize) + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        for (int num : nums) {
            int bucketIdx = (num - minVal) / bucketSize;
            bucketMin[bucketIdx] = Math.min(bucketMin[bucketIdx], num);
            bucketMax[bucketIdx] = Math.max(bucketMax[bucketIdx], num);
        }
        int maxGap = 0;
        int previousMax = minVal; 
        for (int i = 0; i < bucketCount; i++) {
            if (bucketMin[i] == Integer.MAX_VALUE) {
                continue;
            }
            maxGap = Math.max(maxGap, bucketMin[i] - previousMax);
            previousMax = bucketMax[i];
        }

        return maxGap;
    }
}