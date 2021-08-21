import java.util.*

class Permutations {
    lateinit var nums: IntArray
    private val hashMap = HashMap<Int, Int>()
    private val result: MutableList<List<Int>> = mutableListOf()
    
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        this.nums = nums
        nums.forEach {
            hashMap[it] = hashMap.getOrDefault(it, 0) + 1
        }

        recursive(mutableListOf(), nums.size)
        return result
    }

    fun recursive(list: MutableList<Int>, size: Int) {
        if (list.size == size) {
            result.add(mutableListOf<Int>().apply {
                addAll(list)
            })
            return
        }

        hashMap.entries.forEach {
            val key = it.key
            val value = it.value

            if (value == 0) return@forEach

            list.add(key)
            hashMap[key] = value - 1
            recursive(list, size)
            list.removeAt(list.size - 1)
            hashMap[key] = value
        }
    }
}
