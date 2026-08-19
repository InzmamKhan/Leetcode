import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> occupied = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                occupied.put(row, occupied.getOrDefault(row, 0) | (1 << (col - 2)));
            }
        }
        
        int ans = (n - occupied.size()) * 2;
        
        for (int mask : occupied.values()) {
            boolean left = (mask & 0b00001111) == 0;
            boolean right = (mask & 0b11110000) == 0;
            boolean middle = (mask & 0b00111100) == 0;
            
            if (left && right) {
                ans += 2;
            } else if (left || right || middle) {
                ans += 1;
            }
        }
        
        return ans;
    }
}