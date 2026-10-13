import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        for (int i = 0; i < n; i++) {
            long val = nums[i];
            squares[i] = val * val;
        }
        
        Arrays.sort(squares);
        
        long result = 0;
        int k = n / 2;
        
        for (int i = 0; i < k; i++) {
            result -= squares[i];
        }
        
        for (int i = k; i < n; i++) {
            result += squares[i];
        }
        
        return result;
    }
}