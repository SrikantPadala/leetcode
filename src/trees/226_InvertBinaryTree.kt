package trees

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Example:
 * Input: root = `[4,2,7,1,3,6,9]`
 * Output: `[4,7,2,9,6,3,1]`
 */

val sampleTree = TreeNode(
    `val` = 4,
    left = TreeNode(`val` = 2, left = TreeNode(`val` = 1), right = TreeNode(`val` = 3)),
    right = TreeNode(`val` = 7, left = TreeNode(`val` = 6), right = TreeNode(`val` = 9))
)

fun main() {
    invertTree(sampleTree)
    invertTreeIterative(sampleTree)

}

class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

/**
 * Time complexity: O(n)
 * Space complexity: O(h) -> Worst case O(n)
 */
fun invertTree(root: TreeNode?): TreeNode? {
    if (root != null) {
        val left = invertTree(root.left)
        val right = invertTree(root.right)
        root.left = right
        root.right = left
    }
    return root
}

/**
 * Time complexity: O(n)
 * Space complexity: O(n), In a full or complete binary tree, the bottom-most leaf level holds roughly
 * half of all nodes (n/2). Since BFS processes level-by-level, the queue will reach its maximum size
 * when holding this widest level, requiring space proportional to n
 */
fun invertTreeIterative(root: TreeNode?): TreeNode? {
    if (root == null) return null
    val queue: ArrayDeque<TreeNode> = ArrayDeque()
    queue.add(root)

    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()

        // Swap children
        val temp = node.left
        node.left = node.right
        node.right = temp

        node.left?.let { queue.add(it) }
        node.right?.let { queue.add(it) }
    }

    return root
}