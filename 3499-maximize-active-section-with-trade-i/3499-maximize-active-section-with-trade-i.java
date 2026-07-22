class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones++;
            }
        }

        java.util.List<Integer> zeros = new java.util.ArrayList<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                count++;
            } else {
                if (count > 0) {
                    zeros.add(count);
                    count = 0;
                }
            }
        }
        if (count > 0) {
            zeros.add(count);
        }

        if (zeros.size() < 2) {
            return ones;
        }

        int maxGain = 0;
        for (int i = 0; i < zeros.size() - 1; i++) {
            maxGain = Math.max(maxGain, zeros.get(i) + zeros.get(i + 1));
        }

        return ones + maxGain;
    }
}