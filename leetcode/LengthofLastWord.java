class Solution {
    public int lengthOfLastWord(String s) {
        String[] arr = s.split(" ");
        int lastIdx = arr.length - 1;
        
        return arr[lastIdx].length();
    }
}
