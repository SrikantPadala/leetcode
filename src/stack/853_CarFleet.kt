package stack

/**
 * There are n cars at given miles away from the starting mile 0, traveling to reach the mile target.
 *
 * You are given two integer arrays position and speed, both of length n, where position[i] is the starting mile
 * of the ith car and speed[i] is the speed of the ith car in miles per hour.
 *
 * A car cannot pass another car, but it can catch up and then travel next to it at the speed of the slower car.
 *
 * A car fleet is a single car or a group of cars driving next to each other.
 * The speed of the car fleet is the minimum speed of any car in the fleet.
 *
 * If a car catches up to a car fleet at the mile target, it will still be considered as part of the car fleet.
 *
 * Return the number of car fleets that will arrive at the destination.
 *
 * Example 1:
 *
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 *
 * Output: 3
 *
 * Explanation:
 *
 * The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12. The fleet forms at target.
 *
 * The car starting at 0 (speed 1) does not catch up to any other car, so it is a fleet by itself.
 *
 * The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
 */
fun main() {
    val position = intArrayOf(10,8,0,5,3)
    val speed = intArrayOf(2,4,1,1,3)
    val result = carFleet(12, position, speed)
    println(result)
}

/**
 * Compute “time to reach destination” for each car:
 * This tells how long it would take if the car were unimpeded.
 *
 * Sort cars by their starting position in descending order (i.e., from closest to destination to farthest).
 * This ensures we process the “front” cars first.
 *
 * As we process from front to back:
 * Keep track of a “current slowest‐arrival‐time” (let’s call it prevTime).
 * For each car in sorted order, if its time[i] > prevTime, it cannot catch the fleet ahead,
 * so it forms a new fleet and update prevTime = time[i].
 *
 * Otherwise (time ≤ prevTime), this car will catch up and join the fleet ahead (so no new fleet count).
 *
 * Why does this logic work? Because if a car behind would arrive later than the car ahead, it cannot catch up;
 * if it would arrive earlier or at the same time, it will catch up and merge with the fleet ahead.
*/
data class Car(val position: Int, val time: Double)

/**
 * Time complexity: O(nlogn) for sorting
 * Space complexity: O(n) for storing cars with position and time
 */
fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
    // position [10,8,5,3,0] speed [2,4,1,3,1] time [1, 1, 7, 3, 12]
    val cars = position.zip(speed).sortedByDescending { it.first }
        .map { Car(it.first, (target - it.first).toDouble() / it.second) } //sort by position
    var fleet = 0
    var prevTime = 0.0
    for (car in cars) {
        if (car.time > prevTime) {
            fleet++
            prevTime = car.time
        }
    }
    return fleet
}