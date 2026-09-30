class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCounts = new HashMap<>();
        prefixCounts.put(0, 1);

        int sum = 0;
        int result = 0;

        for(int n: nums) {
            sum += n;
            int remaining = sum - k;

            int needed = sum - k;
            result += prefixCounts.getOrDefault(needed, 0);
            
            prefixCounts.merge(sum, 1, Integer::sum);
        }

        return result;
    }
}