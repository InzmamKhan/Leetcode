import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sumAlice = 0;
        int sumBob = 0;
        
        for (int x : aliceSizes) sumAlice += x;
        
        Set<Integer> bobSet = new HashSet<>();
        for (int y : bobSizes) {
            sumBob += y;
            bobSet.add(y); 
        }
        
        int delta = (sumBob - sumAlice) / 2;
        for (int x : aliceSizes) {
            int targetY = x + delta;
            if (bobSet.contains(targetY)) {
                return new int[]{x, targetY};
            }
        }
        
        return new int[0];
    }
}