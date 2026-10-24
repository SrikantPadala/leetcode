package array_and_hashing

/**
 * Encode and Decode Strings
 * Design an algorithm to encode a list of strings to a single string.
 * The encoded string is then decoded back to the original list of strings.
 *
 * Example 1:
 * Input: `["neet","code","love","you"]`
 *
 * Output:`["neet","code","love","you"]`
 *
 * Example 2:
 * Input: `["we","say",":","yes"]`
 *
 * Output: `["we","say",":","yes"]`
 *
 * Constraints:
 * `0 <= strs.length < 100`
 * `0 <= strs[i].length < 200`
 * `strs[i]` contains only UTF-8 characters.
 */
fun main() {
    val list = listOf("we","say",":","yes","!@#$%^&*()")
    val encoded = encode(list)
    println(encoded)
    println(decode(encoded))
}

fun encode(strs: List<String>): String {
    val sb = StringBuilder("")
    for (str in strs) {
        sb.append(str.length.toString() + "#" + str)
    }
    return sb.toString()
}
// 2#we#3#say#1#:#3#yes#10#!@#$%^&*()#
fun decode(str: String): List<String> {
    val result = ArrayList<String>()

    var i = 0
    while (i < str.length) {
        val startOfNumber = i
        while (str[i] != '#') i++
        val endOfNumber = i
        i++
        val lengthOfStr = str.substring(startOfNumber, endOfNumber).toInt()
        val decoded = str.substring(i, i + lengthOfStr)
        result.add(decoded)
        i += decoded.length
    }
    return result
}