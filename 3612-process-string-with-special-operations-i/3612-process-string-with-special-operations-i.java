class Solution {
    public static String removeLast(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return word.substring(0, word.length() - 1);
    }

    public static String reverse(String text) {
        if (text == null) {
            return null;
        }
        return new StringBuilder(text).reverse().toString();
}

    public String processStr(String s) {
        String result = "";
        for(int i=0; i<s.length(); i++){
        char currentLetter = s.charAt(i);
            if(currentLetter == '*'){
                result = removeLast(result);
            }
            else if(currentLetter == '#'){
                result = result + result;
            }
            else if(currentLetter == '%'){
                result = reverse(result);
            }
            else{
                result += s.charAt(i);
            }
        }
        return result;
    }
}