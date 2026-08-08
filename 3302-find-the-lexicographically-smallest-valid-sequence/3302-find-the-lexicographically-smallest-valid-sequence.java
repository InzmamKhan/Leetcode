class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        int[] last = new int[m];
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
        }
        
        int[] ans = new int[m];
        int i = 0;
        j = 0;
        boolean skipped = false;
        
        while (i < n && j < m) {
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            } else if (!skipped && (j == m - 1 || last[j + 1] > i)) {
                ans[j] = i;
                j++;
                skipped = true;
            }
            i++;
        }
        
        return j == m ? ans : new int[0];
    }
}