package problems.kotlin

import kotlin.collections.HashMap

fun main() {
    println(twoSum(intArrayOf(2, 7, 11, 15), 9).contentToString())
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int, Int>()
    for (i in nums.indices) {
        val comp = target - nums[i]
        if (map.containsKey(comp)) {
            return intArrayOf(i, map.get(comp)!!) // !! coz map.get returns nullable
        }
        map.put(nums[i], i)
    }
    return intArrayOf(-1, -1)
}