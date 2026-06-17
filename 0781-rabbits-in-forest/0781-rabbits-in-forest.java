import java.util.HashMap;

class Solution {
    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        
        for (int ans : answers) {
            countMap.put(ans, countMap.getOrDefault(ans, 0) + 1);
        }
        
        int totalRabbits = 0;
        for (HashMap.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int ans = entry.getKey();
            int count = entry.getValue();
            
            int groupSize = ans + 1;
            int groups = (count + groupSize - 1) / groupSize;
            
            totalRabbits += groups * groupSize;
        }
        
        return totalRabbits;
    }
}