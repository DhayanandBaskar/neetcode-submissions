class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> indexByNum = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (indexByNum.containsKey(complement)) {
                return new int[] {indexByNum.get(complement), i};
            }

            indexByNum.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two numbers add up to target");
    }
}
