import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution {

    private static class State {
        int digitIndex;
        int previousDigit;
        int digitBeforePrev;
        boolean isStrictlyLess;
        boolean hasNumberStarted;

        public State(int digitIndex, int previousDigit, int digitBeforePrev, 
                     boolean isStrictlyLess, boolean hasNumberStarted) {
            this.digitIndex = digitIndex;
            this.previousDigit = previousDigit;
            this.digitBeforePrev = digitBeforePrev;
            this.isStrictlyLess = isStrictlyLess;
            this.hasNumberStarted = hasNumberStarted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return digitIndex == state.digitIndex &&
                   previousDigit == state.previousDigit &&
                   digitBeforePrev == state.digitBeforePrev &&
                   isStrictlyLess == state.isStrictlyLess &&
                   hasNumberStarted == state.hasNumberStarted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(digitIndex, previousDigit, digitBeforePrev, isStrictlyLess, hasNumberStarted);
        }
    }

    private Map<State, Long> wavinessCache;
    private Map<String, Long> countCache;

    public int totalWaviness(int num1, int num2) {
        long wavinessUpToNum2 = calculateTotalWavinessUpTo(num2);
        long wavinessUpToNum1Minus1 = calculateTotalWavinessUpTo(num1 - 1);
        
        return (int) (wavinessUpToNum2 - wavinessUpToNum1Minus1);
    }

    private long calculateTotalWavinessUpTo(int limit) {
        if (limit < 100) {
            return 0; 
        }

        String limitString = String.valueOf(limit);
        wavinessCache = new HashMap<>();
        countCache = new HashMap<>();

        return computeWavinessUsingDP(0, -1, -1, false, false, limitString);
    }

    private long computeWavinessUsingDP(int idx, int prev, int prev2, 
                                        boolean isLess, boolean isStarted, String limitStr) {
        if (idx == limitStr.length()) {
            return 0;
        }

        State currentState = new State(idx, prev, prev2, isLess, isStarted);
        if (wavinessCache.containsKey(currentState)) {
            return wavinessCache.get(currentState);
        }

        long totalWavinessCalculated = 0;

        int limitDigit = limitStr.charAt(idx) - '0';
        int maxAllowedDigit = isLess ? 9 : limitDigit;

        for (int currentDigit = 0; currentDigit <= maxAllowedDigit; currentDigit++) {
            
            boolean nextIsLess = isLess || (currentDigit < limitDigit);
            boolean nextIsStarted = isStarted || (currentDigit > 0);

            if (nextIsStarted) {
                
                if (isStarted && prev != -1 && prev2 != -1) {
                    
                    boolean isPeak = (prev > prev2) && (prev > currentDigit);
                    boolean isValley = (prev < prev2) && (prev < currentDigit);

                    if (isPeak || isValley) {
                        long combinationsAhead = countRemainingValidNumbers(idx + 1, nextIsLess, nextIsStarted, limitStr);
                        totalWavinessCalculated += combinationsAhead;
                    }
                }
            }

            int nextPrev = nextIsStarted ? currentDigit : -1;
            int nextPrev2 = nextIsStarted ? prev : -1;

            totalWavinessCalculated += computeWavinessUsingDP(idx + 1, nextPrev, nextPrev2, nextIsLess, nextIsStarted, limitStr);
        }

        wavinessCache.put(currentState, totalWavinessCalculated);
        return totalWavinessCalculated;
    }

    private long countRemainingValidNumbers(int idx, boolean isLess, boolean isStarted, String limitStr) {
        if (idx == limitStr.length()) {
            return 1;
        }

        String cacheKey = idx + "," + isLess + "," + isStarted;
        if (countCache.containsKey(cacheKey)) {
            return countCache.get(cacheKey);
        }

        long validCombinations = 0;
        int limitDigit = limitStr.charAt(idx) - '0';
        int maxAllowedDigit = isLess ? 9 : limitDigit;

        for (int currentDigit = 0; currentDigit <= maxAllowedDigit; currentDigit++) {
            boolean nextIsLess = isLess || (currentDigit < limitDigit);
            boolean nextIsStarted = isStarted || (currentDigit > 0);

            validCombinations += countRemainingValidNumbers(idx + 1, nextIsLess, nextIsStarted, limitStr);
        }

        countCache.put(cacheKey, validCombinations);
        return validCombinations;
    }
}