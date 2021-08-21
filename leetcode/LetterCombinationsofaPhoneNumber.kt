import java.lang.StringBuilder

class LetterCombinations {
    private val result: MutableList<String> = mutableListOf()
    lateinit var digits: String
    private val size by lazy { digits.length }

    fun letterCombinations(digits: String): List<String> {
        this.digits = digits

        recursive(StringBuilder(), 0)
        return result
    }

    private fun recursive(sb: StringBuilder, idx: Int) {
        if (idx == size) {
            if (sb.isNotEmpty())
                result.add(sb.toString())
            return
        }

        val num: Int = digits[idx].digitToInt()

        val start = getStartCharacter(num)
        val cnt = getCharacterCount(num)

        for (i in 0 until cnt) {
            val char = start + i
            val beforeLen = sb.length
            sb.append(char)
            recursive(sb, idx + 1)
            sb.setLength(beforeLen)
        }
    }

    private fun getStartCharacter(num: Int): Char = when(num) {
        2 -> 'a'
        3 -> 'd'
        4 -> 'g'
        5 -> 'j'
        6 -> 'm'
        7 -> 'p'
        8 -> 't'
        9 -> 'w'
        else -> ' '
    }

    private fun getCharacterCount(num: Int) = when(num) {
        7, 9 -> 4
        2, 3, 4, 5, 6, 8 -> 3
        else -> 0
    }
}
