class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {
            int[] tempCount = count.clone();
            boolean possible = true;
            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';
                if (tempCount[c] > 0) {
                    tempCount[c]--;
                } else {
                    possible = false;
                    break;
                }
            }

            if (!possible) continue;

            int targetChar = target.charAt(i) - 'a';
            for (int nextChar = targetChar + 1; nextChar < 26; nextChar++) {
                if (tempCount[nextChar] > 0) {
                    tempCount[nextChar]--;
                    
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.substring(0, i));
                    sb.append((char) ('a' + nextChar));
                    
                    for (int c = 0; c < 26; c++) {
                        while (tempCount[c] > 0) {
                            sb.append((char) ('a' + c));
                            tempCount[c]--;
                        }
                    }
                    return sb.toString();
                }
            }
        }

        return "";
    }
}