class Solution {
    public int jump(int[] nums) {
        int currentpos = 0;
        int count = 0;

        if (nums.length <= 1) {
            return 0;
        }

        while (currentpos < nums.length - 1) {
            int displacementRange = currentpos + nums[currentpos];
            if (displacementRange >= nums.length - 1) {
                count += 1;
                break;
            }

            int maxReachValue = -1;
            int nextBestIndex = currentpos;

            for (int i = currentpos + 1; i <= displacementRange && i < nums.length; i++) {
                int futureReach = i + nums[i]; 
                
                if (futureReach > maxReachValue) {
                    maxReachValue = futureReach;
                    nextBestIndex = i;
                }
            }
            currentpos = nextBestIndex;
            count += 1;
        }

        return count;
    }
}