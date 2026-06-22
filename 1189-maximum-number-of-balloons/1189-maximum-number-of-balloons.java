import java.util.HashMap;
import java.util.Map;

class Solution {
    
    public int maxNumberOfBalloons(String text) {
        text = text.toLowerCase();
        HashMap<Character, Integer> myMap = new HashMap<>();

        int currentValue = 0;
        for (int i = 0; i < text.length(); i++) {
            if (myMap.containsKey(text.charAt(i))) {
                currentValue = myMap.get(text.charAt(i));
                myMap.put(text.charAt(i), (currentValue + 1));
            } else {
                myMap.put(text.charAt(i), 1);
            }
        }

        int bCount = myMap.getOrDefault('b', 0);
        int aCount = myMap.getOrDefault('a', 0);
        int lCount = myMap.getOrDefault('l', 0);
        lCount /= 2;
        int oCount = myMap.getOrDefault('o', 0);
        oCount /= 2;
        int nCount = myMap.getOrDefault('n', 0);

        if (bCount < 1 || aCount < 1 || nCount < 1 || lCount < 1 || oCount < 1) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        min = Math.min(bCount, aCount);
        min = Math.min(min, lCount);
        min = Math.min(min, oCount);
        min = Math.min(min, nCount);

        return min;
    }
}