class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var carry = 0
        var listNode1 = l1
        var listNode2 = l2
        var resultHeadNode: ListNode? = null
        var nowNode: ListNode? = null
        while (listNode1 != null || listNode2 != null) {
            var sum = carry
            
            listNode1?.let {
                sum += it.`val`
                listNode1 = it.next
            }

            listNode2?.let {
                sum += it.`val`
                listNode2 = it.next
            }
            
            carry = sum / 10
            sum %= 10
            
            val listNode = ListNode(sum)
            
            if (nowNode == null) {
                nowNode = listNode
                resultHeadNode = nowNode
            } else {
                nowNode.next = listNode
                nowNode = nowNode.next
            }
        }
        
        if (carry > 0) {
            nowNode?.next = ListNode(carry)
        }
        
        return resultHeadNode
    }
}
