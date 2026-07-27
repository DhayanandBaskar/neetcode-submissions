class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] counts = new char[26];
        for (int i = 0 ; i < s.length(); i++) {
            counts[indexOf(s.charAt(i))]++;
            counts[indexOf(t.charAt(i))]--;
        }

        for (char count: counts) {
            if (count != 0) return false;
        }

        return true;
    }

    private int indexOf(char character) {
        char offset = 'a';
        return character - offset;
    }
}
