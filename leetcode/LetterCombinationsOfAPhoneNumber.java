class Solution {
    private int[] lengthArr = new int[] {0, 0, 3, 3, 3, 3, 3, 4, 3, 4};
    private ArrayList<String> result = new ArrayList<>();
    private int[] array;
    
    public List<String> letterCombinations(String digits) {
        createDigitArray(digits);
        findDigitCombination(0, new StringBuilder());
        
        return result;
    }
    
    private void createDigitArray(String digits) {
        array = new int[digits.length()];
        for(int i = 0; i < array.length; i++) {
            char ch = digits.charAt(i);
            array[i] = ch - '0';
        }
    }
    
    private void findDigitCombination(int k, StringBuilder sb){
        if(k == array.length) {
            if(sb.length() > 0) {
                result.add(sb.toString());
            }
            return;
        }
        
        char[] letters = getDigitToLetters(array[k]);
        for(int i = 0; i < letters.length; i++) {
            sb.append(letters[i]);
            findDigitCombination(k + 1, sb);
            sb.setLength(sb.length() - 1);
        }
    }
    
    private char[] getDigitToLetters(int digit) {
        int length = lengthArr[digit];
        char[] result = new char[length];
        
        int idxAlphabet = 0;
        for(int i = 2; i < digit; i++) {
            idxAlphabet += lengthArr[i];
        }
        
        for(int i = 0; i < length; i++) {
            result[i] = (char) ('a' + (idxAlphabet + i));
        }
        
        return result;
    }
}
