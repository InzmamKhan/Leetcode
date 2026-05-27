class Solution {
    public int numberOfSpecialChars(String word) {
        // Arrays to store indices (initialized to -1)
        int[] lastLower = new int[26];
        int[] firstUpper = new int[26];
        
        java.util.Arrays.fill(lastLower, -1);
        java.util.Arrays.fill(firstUpper, -1);
        
        // Single pass to record positions
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            
            if (Character.isLowerCase(ch)) {
                int idx = ch - 'a';
                lastLower[idx] = i;
            } else {
                int idx = ch - 'A';
                // Only record the FIRST occurrence of the uppercase letter
                if (firstUpper[idx] == -1) {
                    firstUpper[idx] = i;
                }
            }
        }
        
        int specialCount = 0;
        
        // Verify the conditions for all 26 alphabets
        for (int i = 0; i < 26; i++) {
            if (lastLower[i] != -1 && firstUpper[i] != -1) {
                if (lastLower[i] < firstUpper[i]) {
                    specialCount++;
                }
            }
        }
        
        return specialCount;
    }
}