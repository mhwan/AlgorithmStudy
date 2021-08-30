class Solution {
    fun getConcatenation(nums: IntArray): IntArray {
        val result = IntArray(nums.size * 2)
        
        nums.forEachIndexed { index, i ->
            result[index] = i
            result[index + nums.size] = i
        }
        
        return result
    }
}
