class Solution {
    static int findTheIndexOf(int num, int[] nums){
        for(int i=0; i<nums.length; i++){
            if(nums[i] == num){
                return i;
            }
        }
        return -1;
    }

    static int findTheGreaterElement(int index, int[] nums, int reference){
        for(int i = index + 1; i < nums.length; i++){
            if(nums[i] > reference){
                return nums[i]; 
            }
        }
        return -1;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if(nums1.length == 0){
            return nums1;
        }

        int[] result = new int[nums1.length];

        for(int i=0; i<nums1.length; i++){
            int index = findTheIndexOf(nums1[i], nums2);
            int greaterElement = findTheGreaterElement(index, nums2, nums1[i]);

            result[i] = greaterElement;
        }
        return result;
    }
}