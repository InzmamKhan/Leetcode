import java.util.*;

class Solution {
    static class Interval {
        int l, r, weight, originalIndex;

        Interval(int l, int r, int weight, int originalIndex) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.originalIndex = originalIndex;
        }
    }

    static class State {
        long sumWeight;
        List<Integer> indices;

        State(long sumWeight, List<Integer> indices) {
            this.sumWeight = sumWeight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> cur = intervals.get(i);
            arr[i] = new Interval(cur.get(0), cur.get(1), cur.get(2), i);
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a.r, b.r));

        int[] rBoundary = new int[n];
        for (int i = 0; i < n; i++) {
            rBoundary[i] = arr[i].r;
        }

        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0, new ArrayList<>());
            }
        }

        for (int i = 1; i <= n; i++) {
            Interval cur = arr[i - 1];
            int prevIdx = binarySearch(rBoundary, cur.l);

            for (int k = 1; k <= 4; k++) {
                dp[i][k] = cloneState(dp[i - 1][k]);

                State prevBest = dp[prevIdx + 1][k - 1];
                if (k == 1 || prevBest.sumWeight > 0 || prevIdx + 1 == 0) {
                    long candWeight = prevBest.sumWeight + cur.weight;
                    List<Integer> candList = new ArrayList<>(prevBest.indices);
                    candList.add(cur.originalIndex);
                    Collections.sort(candList);

                    State candState = new State(candWeight, candList);
                    if (isBetter(candState, dp[i][k])) {
                        dp[i][k] = candState;
                    }
                }
            }
        }

        State best = new State(0, new ArrayList<>());
        for (int k = 1; k <= 4; k++) {
            if (isBetter(dp[n][k], best)) {
                best = dp[n][k];
            }
        }

        int[] result = new int[best.indices.size()];
        for (int i = 0; i < best.indices.size(); i++) {
            result[i] = best.indices.get(i);
        }
        return result;
    }

    private int binarySearch(int[] rBoundary, int targetL) {
        int low = 0, high = rBoundary.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (rBoundary[mid] < targetL) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private State cloneState(State s) {
        return new State(s.sumWeight, new ArrayList<>(s.indices));
    }

    private boolean isBetter(State a, State b) {
        if (a.sumWeight != b.sumWeight) {
            return a.sumWeight > b.sumWeight;
        }
        int lenA = a.indices.size();
        int lenB = b.indices.size();
        int minLen = Math.min(lenA, lenB);

        for (int i = 0; i < minLen; i++) {
            int cmp = Integer.compare(a.indices.get(i), b.indices.get(i));
            if (cmp != 0) {
                return cmp < 0;
            }
        }
        return lenA < lenB;
    }
}