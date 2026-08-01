class Solution {
    public boolean predictTheWinner(int[] nums) {
        return getNetScore(nums, 0, nums.length - 1) >= 0;
    }

    private int getNetScore(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int pickLeft = nums[left] - getNetScore(nums, left + 1, right);
        int pickRight = nums[right] - getNetScore(nums, left, right - 1);

        return Math.max(pickLeft, pickRight);
    }
}