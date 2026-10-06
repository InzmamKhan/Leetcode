class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int mismatch = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    mismatch++;
                }
            }
        }

        return open + mismatch;
    }
}