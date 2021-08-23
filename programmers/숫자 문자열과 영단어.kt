import java.lang.StringBuilder

fun main() {
    println(NumberToString().solution("one4seveneight"))
}
class NumberToString {
    private val numberMap = mapOf(
        "zero" to 0,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun solution(s: String): Int {
        val sb = StringBuilder()

        var startIdx = 0
        var endIdx = 0
        while (startIdx < s.length || endIdx < s.length) {
            val ch = s[endIdx++]

            var appendStr: String? = null
            if (ch.isDigit()) {
                appendStr = ch.toString()
            } else {
                if (endIdx - startIdx >= 3) {
                    val str = s.substring(startIdx, endIdx)
                    if (numberMap.containsKey(str)) {
                        appendStr = numberMap[str].toString()
                    }
                }
            }

            appendStr?.let {
                sb.append(it)
                startIdx = endIdx
                appendStr = null
            }
        }

        return sb.toString().toInt()
    }
}
