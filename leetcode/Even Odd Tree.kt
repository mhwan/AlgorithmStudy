import java.util.*

class Tree {
    fun isEvenOddTree(root: TreeNode?): Boolean {
        var level = 0
        val queue = LinkedList<TreeNode>().apply {
            offer(root)
        }

        while (queue.isNotEmpty()) {
            val isIncreasing = isEven(level)
            var beforeValue = if (isIncreasing) Int.MIN_VALUE else Int.MAX_VALUE
            val size = queue.size
            for (i in 0 until size) {
                val treeNode = queue.poll()

                if ((isIncreasing && beforeValue >= treeNode.`val`) ||
                    (!isIncreasing && beforeValue <= treeNode.`val`) ||
                    (isIncreasing && isEven(treeNode.`val`)) ||
                    (!isIncreasing && !isEven(treeNode.`val`))
                ) {
                    return false
                }

                beforeValue = treeNode.`val`

                treeNode.left?.let {
                    queue.offer(it)
                }
                treeNode.right?.let {
                    queue.offer(it)
                }
            }
            level++
        }

        return true
    }

    private fun isEven(level: Int) = (level % 2) == 0
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
