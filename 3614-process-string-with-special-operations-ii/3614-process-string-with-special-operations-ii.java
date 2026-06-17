import java.util.*;

class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] lengths = new long[n];
        long currentLen = 0;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '*') {
                if (currentLen > 0) currentLen--;
            }
            else if (ch == '#') {
                currentLen *= 2;
            }
            else if (ch == '%') {
            }
            else {
                currentLen++;
            }
            lengths[i] = currentLen;
        }

        if (k < 0 || k >= currentLen) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            long lenBeforeOp = (i == 0) ? 0 : lengths[i - 1];

            if (ch == '*') {
                continue;
            } 
            else if (ch == '#') {
                if (k >= lenBeforeOp) {
                    k %= lenBeforeOp;
                }
            } 
            else if (ch == '%') {
                k = lenBeforeOp - 1 - k;
            } 
            else {
                if (k == lenBeforeOp) {
                    return ch;
                }
            }
        }

        return '.';
    }
}