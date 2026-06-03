import java.util.HashMap;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // Traditional simple for-loop
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];

            if (map.containsKey(currentNum)) {
                int lastSeenIndex = map.get(currentNum);

                if (i - lastSeenIndex <= k) {
                    return true;
                }
            }

            map.put(currentNum, i);
        }
        
        return false;
    }
}