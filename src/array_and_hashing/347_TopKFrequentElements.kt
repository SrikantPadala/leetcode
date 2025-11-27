package array_and_hashing

import java.util.PriorityQueue

fun main() {
    println(topKFrequentBucketSort(intArrayOf(1,2,1,2,1,2,3,1,3,2), 2).contentToString())
}
/*
    Time complexity O(n)
    Space complexity O(n) - for freqMap + buckets
 */
fun topKFrequentBucketSort(nums: IntArray, k: Int): IntArray {
    val freqMap = mutableMapOf<Int, Int>()
    // Step 1: Build frequency map
    for (num in nums) {
        freqMap[num] = freqMap.getOrDefault(num, 0) + 1
    }
    // Step 2: Create buckets (index = frequency)
    // maxFreq can be size of array if all elements are same
    val buckets = Array<MutableList<Int>>(nums.size + 1) { mutableListOf() }
    for ((num, freq) in freqMap) {
        buckets[freq].add(num)
    }
    // Step 3: Collect top k frequent elements from buckets (from high freq to low)
    val result = IntArray(k)
    var counter = 0
    for (i in nums.size downTo 0) {
        for (num in buckets[i]) {
            result[counter++] = num
            if (counter == k) return result
        }
    }
    return intArrayOf() // Should not reach here if k is valid
}


/*
    Time complexity O(n log k)
    Space complexity O(n) - for freqMap
 */
fun topKFrequentMinHeap(nums: IntArray, k: Int): IntArray {
    data class Node(val num: Int, val freq: Int)

    val freqMap = HashMap<Int, Int>()
    for (num in nums) {
        freqMap[num] = (freqMap[num] ?: 0) + 1
    }
    // keep a min heap, so that the remaining k elements left are having max freq
    val pq = PriorityQueue<Node>(compareBy { it.freq })
    for ((num, count) in freqMap) {
        pq.offer(Node(num, count))
        if (pq.size > k) {
            pq.poll()
        }
    }
    val result = IntArray(k)
    for (i in result.indices) {
        result[i] = pq.poll().num
    }
    return result
}

/*
    Time complexity O(n log n)
    Space complexity O(n)
 */
fun topKFrequentPQInsertAll(nums: IntArray, k: Int): IntArray {
    data class Node(val num: Int, val count: Int)

    val hashMap = HashMap<Int, Int>()
    nums.forEach { num ->
        hashMap[num] = hashMap.getOrDefault(num, 0) + 1
    }
    val pq: PriorityQueue<Node> = PriorityQueue<Node>(compareByDescending { it.count })
    hashMap.forEach {
        pq.offer(Node(it.key, it.value))
    }
    val result = IntArray(k)
    for (i in 0 until k) {
        result[i] = pq.poll().num
    }
    return result
}

/*
    Time complexity O(n log n)
    Space complexity O(n)
 */
fun topKFrequentSorting(nums: IntArray, k: Int): IntArray {
    data class Node(val num: Int, val freq: Int)

    val freqMap = HashMap<Int, Int>()
    for (num in nums) {
        freqMap[num] = freqMap.getOrDefault(num, 0) + 1
    }

    val arr = mutableListOf<Node>()
    for ((num, freq) in freqMap) {
        arr.add(Node(num, freq))
    }
    arr.sortByDescending { it.freq }

    val res = IntArray(k)
    for (i in 0 until k) {
        res[i] = arr[i].num
    }
    return res
}
