class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 0;

        for (int num : nums) {
            int lenPlusOne = dp.getOrDefault(num, 0) + 1;
            int lenSame = dp.getOrDefault(num - 1, 0) + 1;

            dp.put(num + 1, lenPlusOne);
            dp.put(num, lenSame);

            maxLen = Math.max(maxLen, Math.max(lenPlusOne, lenSame));
        }

        return maxLen;
    }
}