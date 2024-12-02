import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val left = arrayListOf(5000)
        val right = arrayListOf(5000)
        var total = 0

        input.forEach {
            val strings = it.split("   ")
            left.add(strings[0].toInt())
            right.add(strings[1].toInt())
        }

        left.sort()
        right.sort()

        left.forEachIndexed { index, i ->
            total += abs(i - right[index])
        }

        return total
    }

    fun part2(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val countByInt = mutableMapOf<Int, Int>()
        var total = 0

        input.forEach {
            var strings = it.split("   ", limit = 2)
            left.add(strings[0].toInt())

            val rightInt = strings[1].toInt()
            countByInt.putIfAbsent(rightInt, 0)
            countByInt[rightInt] = countByInt[rightInt]!! + 1
        }

        left.forEach {
            total += it * countByInt.getOrDefault(it, 0).toInt()
        }

        return total
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

