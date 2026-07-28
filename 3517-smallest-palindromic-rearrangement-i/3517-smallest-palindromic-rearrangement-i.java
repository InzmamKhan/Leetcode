import java.util.Arrays;

class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int halfLen = n / 2;
        
        char[] half = s.substring(0, halfLen).toCharArray();
        Arrays.sort(half);
        
        StringBuilder sb = new StringBuilder();
        sb.append(half);
        
        if (n % 2 != 0) {
            sb.append(s.charAt(halfLen));
        }
        
        for (int i = half.length - 1; i >= 0; i--) {
            sb.append(half[i]);
        }
        
        return sb.toString();
    }
}