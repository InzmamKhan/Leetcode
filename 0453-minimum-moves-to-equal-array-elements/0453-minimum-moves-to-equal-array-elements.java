class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0], moves = 0;
        for (int n : nums) {
            min = Math.min(min, n);
        }
        for (int n : nums) {
            moves += n - min;
        }
        return moves;
    }
}