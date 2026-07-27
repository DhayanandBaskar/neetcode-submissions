class Solution {
    public boolean hasDuplicate(int[] nums) {
        HashSet<Integer> visited = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++) {
            if (visited.contains(nums[i])) {
                return true;
            }
            visited.add(nums[i]);
        }

        return false;
    }
}