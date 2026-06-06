class Solution {
    public int[] leftRightDifference(int[] nums) {
        if (nums.length <= 1) {
            int[] edgeResult = {0};
            return edgeResult; 
        }

        int currentLeftSum = 0;
        int[] leftSum = new int[nums.length];

        int currentRightSum = 0;
        int[] rightSum = new int[nums.length];

        int leftFlag = 0;
        while (leftFlag < nums.length) {
            int temp = nums[leftFlag];
            leftSum[leftFlag] = currentLeftSum;
            currentLeftSum += temp;

            leftFlag++;
        }

        int rightFlag = nums.length - 1;
        while (rightFlag >= 0) {
            int temp = nums[rightFlag];
            rightSum[rightFlag] = currentRightSum;
            currentRightSum += temp;

            rightFlag--;
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = Math.abs(leftSum[i] - rightSum[i]);
        }

        return result;
    }
}