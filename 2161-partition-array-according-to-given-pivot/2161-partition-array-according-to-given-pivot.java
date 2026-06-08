class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] result = new int[n];
        
        int lessCount = 0;
        int equalCount = 0;
        
        for (int i = 0; i < n; i++) {
            int num = nums[i]; 
            if (num < pivot) {
                lessCount++;
            } else if (num == pivot) {
                equalCount++;
            }
        }
        
        int left = 0;                      
        int middle = lessCount;            
        int right = lessCount + equalCount;
        
        for (int i = 0; i < n; i++) {
            int num = nums[i];

            if (num < pivot) {
                result[left] = num;
                left = left + 1;    
            }
            else if (num > pivot) {
                result[right] = num;
                right = right + 1; 
            }
            else {
                result[middle] = num;
                middle = middle + 1;
            }
        }
        
        return result;
    }
}