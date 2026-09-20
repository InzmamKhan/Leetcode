class Solution {
    public int reverseDegree(String s) {
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int reversedAlphabetIndex = 26 - (s.charAt(i) - 'a');
            int stringIndex = i + 1;
            total += reversedAlphabetIndex * stringIndex;
        }
        return total;
    }
}