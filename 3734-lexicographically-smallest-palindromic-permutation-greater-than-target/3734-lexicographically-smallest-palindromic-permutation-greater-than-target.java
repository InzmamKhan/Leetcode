class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int halfLen = n / 2;
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        if ((n % 2 == 0 && oddCount > 0) || (n % 2 != 0 && oddCount != 1)) {
            return "";
        }

        int[] halfFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        for (int prefixLen = halfLen; prefixLen >= 0; prefixLen--) {
            int[] currentFreq = halfFreq.clone();
            boolean possible = true;
            for (int i = 0; i < prefixLen; i++) {
                int idx = target.charAt(i) - 'a';
                if (currentFreq[idx] <= 0) {
                    possible = false;
                    break;
                }
                currentFreq[idx]--;
            }

            if (!possible) {
                continue;
            }

            int startNextChar = 0;
            if (prefixLen < halfLen) {
                startNextChar = target.charAt(prefixLen) - 'a' + 1;
            }

            for (int c = startNextChar; c < 26; c++) {
                if (c == 0 && prefixLen == halfLen) {
                    StringBuilder halfSb = new StringBuilder(target.substring(0, halfLen));
                    String candidateHalf = halfSb.toString();
                    StringBuilder fullSb = new StringBuilder(candidateHalf);
                    if (n % 2 != 0) {
                        fullSb.append(midChar);
                    }
                    for (int i = halfLen - 1; i >= 0; i--) {
                        fullSb.append(candidateHalf.charAt(i));
                    }

                    String fullCandidate = fullSb.toString();
                    if (fullCandidate.compareTo(target) > 0) {
                        return fullCandidate;
                    }
                    continue;
                }

                if (currentFreq[c] > 0) {
                    int[] nextFreq = currentFreq.clone();
                    nextFreq[c]--;

                    StringBuilder halfSb = new StringBuilder();
                    halfSb.append(target.substring(0, prefixLen));
                    halfSb.append((char) ('a' + c));
                    for (int i = 0; i < 26; i++) {
                        for (int k = 0; k < nextFreq[i]; k++) {
                            halfSb.append((char) ('a' + i));
                        }
                    }

                    String candidateHalf = halfSb.toString();
                    StringBuilder fullSb = new StringBuilder(candidateHalf);
                    if (n % 2 != 0) {
                        fullSb.append(midChar);
                    }
                    for (int i = halfLen - 1; i >= 0; i--) {
                        fullSb.append(candidateHalf.charAt(i));
                    }

                    String fullCandidate = fullSb.toString();
                    if (fullCandidate.compareTo(target) > 0) {
                        return fullCandidate;
                    }
                }
            }
        }

        return "";
    }
}