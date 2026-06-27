import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put((long) num, countMap.getOrDefault((long) num, 0) + 1);
        }

        int maxLen = 0;

        if (countMap.containsKey(1L)) {
            int countOne = countMap.get(1L);
            maxLen = countOne % 2 == 1 ? countOne : countOne - 1;
        }

        for (long key : countMap.keySet()) {
            if (key == 1) continue;

            int currentLen = 0;
            long currentNum = key;

            while (countMap.containsKey(currentNum) && countMap.get(currentNum) >= 2) {
                currentLen += 2;
                currentNum = currentNum * currentNum;
            }

            if (countMap.containsKey(currentNum) && countMap.get(currentNum) >= 1) {
                currentLen += 1;
            } else {
                currentLen -= 1; 
            }

            maxLen = Math.max(maxLen, currentLen);
        }

        return maxLen;
    }
}