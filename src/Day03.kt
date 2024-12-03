private const val DAY = "03"
private const val DAY_NAME = "Day$DAY"

fun main() {

    fun part1(input: List<String>): Int {
        val matcher = "mul\\((\\d+),(\\d+)\\)".toRegex()
        val all = input.joinToString(separator = "")
        val allMatched = matcher.findAll(all)
        val ints = allMatched.map { it.groupValues[1].toInt() * it.groupValues[2].toInt() }.toList()
        return ints.sum()
    }

    fun part2(input: List<String>): Int {
        val all = "do()" + input.joinToString(separator = "") + "don't()"
        val cleanMatcher = "do\\(\\)(.+?)don't\\(\\)".toRegex()
        val cleaned = cleanMatcher.findAll(all).map { it.groupValues[1] }.toList()
        return part1(cleaned)
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("${DAY_NAME}_test")
    check(part2(testInput) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput(DAY_NAME)
//    part1(input).println()
    part2(input).println()
}

