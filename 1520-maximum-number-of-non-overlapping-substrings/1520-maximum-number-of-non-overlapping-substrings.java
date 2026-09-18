class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        List<String> result = new ArrayList<>();
        int lastRight = -1;

        for (int i = 0; i < n; i++) {
            if (i == first[s.charAt(i) - 'a']) {
                int newRight = checkValid(s, i, first, last);
                if (newRight != -1) {
                    if (i > lastRight) {
                        result.add("");
                    }
                    lastRight = newRight;
                    result.set(result.size() - 1, s.substring(i, lastRight + 1));
                }
            }
        }

        return result;
    }

    private int checkValid(String s, int left, int[] first, int[] last) {
        int right = last[s.charAt(left) - 'a'];
        for (int i = left; i <= right; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] < left) {
                return -1;
            }
            right = Math.max(right, last[ch]);
        }
        return right;
    }
}