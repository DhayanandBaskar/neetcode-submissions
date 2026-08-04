class Solution {
    public boolean hasDuplicate(int[] nums) {
        HashSet<Integer> visited = new HashSet<>();
        for(int n: nums) {
            if (visited.contains(n)) {
                return true;
            }
            visited.add(n);
        }

        return false;
    }
}