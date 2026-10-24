package array_and_hashing

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * Input: strs = `["eat","tea","tan","ate","nat","bat"]`
 *
 * Output: `[["bat"],["nat","tan"],["ate","eat","tea"]]`
 */
fun main() {
    val strs = arrayOf("eat","tea","tan","ate","nat","bat")
    println(groupAnagrams4(strs))
}

/*
    Time complexity
    ---------------
    Let n = number of strings, and k = maximum length of a string
    Sorting each string: O(k log k)
    For n strings: O(n * k log k)

    Space Complexity:
    -----------------
    Sorting allocates a new CharArray and String → O(n * k) if not in place when using sortedArray()
    groupBy stores keys and lists → O(n * k) overall

 */
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    return strs.groupBy { String(it.toCharArray().apply { sort() }) }.values.toList()
}

fun groupAnagrams1(strs: Array<String>): List<List<String>> {
    return strs.groupBy { str ->
        val count = IntArray(26)
        for (ch in str) count[ch - 'a']++
        count.joinToString("#") // avoids collisions
    }.values.toList()
}

/*
    Time Complexity: O(n * k)
    Space Complexity: Still O(n * k)
 */
fun groupAnagrams2(strs: Array<String>): List<List<String>> {
    fun hash(counts: IntArray): Int {
        var result = 1
        for (element in counts) result = 31 * result + element
        return result
    }
    return strs.groupBy { str ->
        val counts = IntArray(26)
        str.forEach { counts[it - 'a']++ }
        hash(counts) // still need contentEquals/ because collisions can happen, its not fully correct solution
    }.values.toList()
}

/*
    Time Complexity: O(n * k)
    Space Complexity: Still O(n * k)

    The AnagramKey version is faster not because its complexity is better, but because:

    - It minimizes heap allocation
    - It uses raw primitives efficiently
    - It avoids expensive string manipulation
 */
fun groupAnagrams3(strs: Array<String>): List<List<String>> {
    data class AnagramKey(val freq: IntArray) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is AnagramKey) return false
            return freq.contentEquals(other.freq)
        }

        override fun hashCode(): Int {
            return freq.contentHashCode()
        }
    }
    val map = mutableMapOf<AnagramKey, MutableList<String>>()

    for (str in strs) {
        val count = IntArray(26)
        for (ch in str) {
            count[ch - 'a']++
        }
        val key = AnagramKey(count)
        map.computeIfAbsent(key) { mutableListOf() }.add(str)
    }

    return map.values.toList()
}

fun groupAnagrams4(strs: Array<String>): List<List<String>> {
    data class AnagramKey(val freq: IntArray) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is AnagramKey) return false
            return freq.contentEquals(other.freq)
        }

        override fun hashCode(): Int {
            return freq.contentHashCode()
        }
    }

    return strs.groupBy { str ->
        val counts = IntArray(26)
        str.forEach { counts[it - 'a']++ }
        AnagramKey(counts)
    }.values.toList()
}