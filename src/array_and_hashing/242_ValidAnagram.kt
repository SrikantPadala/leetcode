package array_and_hashing

fun main() {
    val correct = "\uD83D\uDE03"  // 😃
    val broken  = "\uDE03\uD83D"  // INVALID surrogate order — not a real emoji

    println("Safe version:     " + isAnagramNotUnicodeSafe(correct, broken))
    println("Unicode version:  " + isAnagramCodePointSafe(correct, broken))
}

/*
Given two strings s and t, return true if t is an anagram of s, and false otherwise.
Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

Constraints:
1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
/*
    Time Complexity - O(n)
    Space Complexity - O(1)
 */
fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false
    val freq = IntArray(26)
    for (i in s.indices) {
        freq[s[i] - 'a']++
        freq[t[i] - 'a']--
    }
    return freq.all { it == 0 }
}

fun isAnagramNotUnicodeSafe(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    val map = HashMap<Int, Int>()

    for (cp in s) {
        map[cp.code] = map.getOrDefault(cp.code, 0) + 1
    }

    for (cp in t) {
        val count = map[cp.code] ?: return false
        if (count == 0) return false
        map[cp.code] = count - 1
    }

    return true
}


/*
Why do we need codePoints()?
Because:
Kotlin/Java Char is NOT a full Unicode character.
It is a UTF-16 code unit (16 bits).
Some characters — such as:
emoji 😊
flags 🇮🇳
musical symbols 🎼
mathematical symbols 𝛀
rare CJK characters

cannot fit in 16 bits.
They are encoded as a surrogate pair → 2 Chars.
 */
/*
    Time Complexity - O(n)
    Space Complexity - O(n)
 */
fun isAnagramCodePointSafe(s: String, t: String): Boolean {
    val sPoints = s.codePoints().toArray()
    val tPoints = t.codePoints().toArray()

    if (sPoints.size != tPoints.size) return false

    val map = HashMap<Int, Int>()
    for (cp in sPoints) {
        map[cp] = map.getOrDefault(cp, 0) + 1
    }
    for (cp in tPoints) {
        val count = map[cp] ?: return false
        if (count == 0) return false
        map[cp] = count - 1
    }

    return true
}
