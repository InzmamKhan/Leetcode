import java.util.HashMap;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Boolean> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int currentNumber = nums[i];

            if (seen.containsKey(currentNumber)) {
                return true; 
            }

            seen.put(currentNumber, true);
        }
        return false;
    }
}