class Solution {
    private void update(int[] bit, int idx, int val, int size) {
        for (; idx < size; idx += idx & -idx) {
            bit[idx] += val;
        }
    }

    private int query(int[] bit, int idx) {
        int sum = 0;
        for (; idx > 0; idx -= idx & -idx) {
            sum += bit[idx];
        }
        return sum;
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        int bitSize = 2 * n + 5;
        int[] bit = new int[bitSize];
        
        int offset = n + 2;
        
        long totalSubarrays = 0;
        int currentBalance = 0;
        
        update(bit, 0 + offset, 1, bitSize);
        
        for (int num : nums) {
            if (num == target) {
                currentBalance += 1;
            } else {
                currentBalance -= 1;
            }
            
            totalSubarrays += query(bit, currentBalance + offset - 1);
            
            update(bit, currentBalance + offset, 1, bitSize);
        }
        
        return totalSubarrays;
    }
}