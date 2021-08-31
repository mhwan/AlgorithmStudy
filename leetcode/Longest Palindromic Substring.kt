class Solution {
     fun longestPalindrome(s: String): String {
        var longestStr = ""
        val size = s.length
        val dpArray = Array<BooleanArray>(size){BooleanArray(size)}

        for (i in s.indices) {
            longestStr = s[i].toString()
            dpArray[i][i] = true
        }

        for (i in 0 until size - 1){
            if (s[i] == s[i+1]) {
                longestStr = s.substring(i, i+2)
                dpArray[i][i+1] = true
            }
        }

        for (len in 3..size) {
            for (start in 0..(size - len)) {
                val end = start + len - 1
                if (dpArray[start + 1][end - 1] &&
                        s[start] == s[end]) {
                    longestStr = s.substring(start, end + 1)
                    dpArray[start][end] = true
                }
            }
        }


        return longestStr
    }
}
