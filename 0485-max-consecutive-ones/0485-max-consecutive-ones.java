class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        int max = 0;
        int currentbest = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                currentbest++;
            } else {
                max = Math.max(currentbest, max);
                currentbest = 0; 
            }
        }
        
        return Math.max(max, currentbest);
    }
}