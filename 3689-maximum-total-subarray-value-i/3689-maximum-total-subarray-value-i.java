class Solution {
    public long maxTotalValue(int[] nums, int k) {
        if(nums.length == 0){
            return 0;
        }


        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i=0; i<nums.length; i++){
            if(max < nums[i]){
                max = nums[i];
            }
            if(min > nums[i]){
                min = nums[i];
            }
        }
        long result = (long)max - (long)min;
        result *= k;
        return result;
    }
}