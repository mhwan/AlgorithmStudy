import java.lang.StringBuilder
import java.util.*

class Solution {
    private var selectRow = 0
    private var size = 0
    private val stack: Stack<Int> = Stack()

    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        selectRow = k
        size = n
        cmd.forEach {
            process(it)
        }

        return parseResult(size)
    }

    private fun process(command: String){
        when(getCommand(command)) {
            'U' -> upTo(command)
            'D' -> downTo(command)
            'C' -> clear()
            'Z' -> undo()
        }
    }

    private fun upTo(string: String) {
        val value = getValue(string).toInt()
        selectRow -= value
    }

    private fun downTo(string: String) {
        val value = getValue(string).toInt()
        selectRow += value
    }

    private fun clear() {
        stack.push(selectRow)
        size--
        if (selectRow >= size) {
            selectRow = size - 1
        }
    }

    private fun undo() {
        val idx = stack.pop()
        size++

        if (selectRow >= idx) selectRow++
    }

    private fun getCommand(string: String) = string[0]

    private fun getValue(string: String) = string.split(" ")[1]

    private fun parseResult(size: Int): String{
        val sb = StringBuilder()
        for (i in 0 until size) {
            sb.append("O")
        }

        while (stack.isNotEmpty()) {
            sb.insert(stack.pop(), 'X')
        }
        return sb.toString()
    }
}
