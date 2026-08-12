import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            // Expand window by adding current element
            count.put(nums[right], count.getOrDefault(nums[right], 0) + 1);

            // Shrink window if current element's frequency exceeds k
            while (count.get(nums[right]) > k) {
                count.put(nums[left], count.get(nums[left]) - 1);
                left++;
            }

            // Update answer with valid window size
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}