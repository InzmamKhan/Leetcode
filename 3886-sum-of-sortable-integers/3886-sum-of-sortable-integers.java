class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k == 0) {
                if (isSortable(nums, k)) {
                    sum += k;
                }
            }
        }
        return sum;
    }

    private boolean isSortable(int[] nums, int k) {
        int n = nums.length;
        int numBlocks = n / k;
        int prevMax = Integer.MIN_VALUE;

        for (int i = 0; i < numBlocks; i++) {
            int start = i * k;
            int drops = 0;
            int dropIdx = -1;

            for (int j = 0; j < k; j++) {
                if (nums[start + j] > nums[start + (j + 1) % k]) {
                    drops++;
                    dropIdx = j;
                }
            }

            if (drops > 1) {
                return false;
            }

            int minVal, maxVal;
            if (drops == 0) {
                minVal = nums[start];
                maxVal = nums[start + k - 1];
            } else {
                minVal = nums[start + (dropIdx + 1) % k];
                maxVal = nums[start + dropIdx];
            }

            if (i > 0 && prevMax > minVal) {
                return false;
            }
            prevMax = maxVal;
        }
        return true;
    }
}