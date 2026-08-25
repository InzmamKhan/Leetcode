import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> mymap = new HashSet<>();
        for (int num : nums) {
            mymap.add(num);
        }

        int currentNum = k;
        while (mymap.contains(currentNum)) {
            currentNum += k; 
        }

        return currentNum;
    }
}