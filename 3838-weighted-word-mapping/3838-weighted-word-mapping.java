class Solution {
    public static String equivalentLetter(int value) {
        int remainder = value % 26;
        char letter = (char) ('z' - remainder);
        return String.valueOf(letter);
    }

    public String mapWordWeights(String[] words, int[] weights) {
        String resultStr = "";

        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];
            int letterWeight = 0;

            for (int j = 0; j < currentWord.length(); j++) {
                char ch = currentWord.charAt(j);
                int alphabetIndex = ch - 'a'; 
                letterWeight += weights[alphabetIndex];
            }

            resultStr = resultStr + equivalentLetter(letterWeight);
        }
        
        return resultStr;
    }
}