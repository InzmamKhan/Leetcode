public class Solution {
    public void moveZeroes(int[] nums) {
        int writePointer = 0;

        for (int readPointer = 0; readPointer < nums.length; readPointer++) {
            if (nums[readPointer] != 0) {
                nums[writePointer] = nums[readPointer];
                writePointer++; 
            }
        }

        while (writePointer < nums.length) {
            nums[writePointer] = 0;
            writePointer++;
        }
    }
}