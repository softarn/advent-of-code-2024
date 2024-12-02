private const val DAY = "03"
private const val DAY_NAME = "Day$DAY"

fun main() {

    fun part1(input: List<String>): Int {
        return 1
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("${DAY_NAME}_test")
    check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput(DAY_NAME)
    part1(input).println()
    part2(input).println()
}

