class Solution {

    static int sizeOfSlidingWindow(int index, String s, String gooal) {
        int max = 0;
        int sIndex = 0; 

        for (int i = index; i < gooal.length(); i++) {
            if (sIndex >= s.length()) {
                break;
            }

            if (s.charAt(sIndex) == gooal.charAt(i)) {
                max++;
                sIndex++;
            }
            else {
                break;
            }
        }
        return max; 
    }

    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        String gooal = goal + goal;
        int length = s.length();
        int maxBest = 0;

        for (int i = 0; i < gooal.length(); i++) {
            if (gooal.charAt(i) == s.charAt(0)) {
                maxBest = sizeOfSlidingWindow(i, s, gooal);

                if (maxBest == length) {
                    return true;
                }
            }
        }
        return false;
    }
}