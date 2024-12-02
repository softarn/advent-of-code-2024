import kotlin.math.abs

fun main() {
    fun isDecOrInc(
        cleaned: List<Int>,
        index: Int,
        i: Int,
        comparing: String
    ): Boolean {
        val diff = cleaned[index - 1] - i

        if (abs(diff) > 3 || abs(diff) < 1) {
            return false
        }

        if (diff > 0 && comparing == "gt") {
            return false
        }

        if (diff < 0 && comparing == "lt") {
            return false
        }

        return true
    }
    fun isDecOrInc(it: List<Int>): Boolean {
        var comparing = "lt"

        it.forEachIndexed { index, i ->
            if (index == 0) {
                if (i < it[index + 1]) {
                    comparing = "gt"
                } else {
                    comparing = "lt"
                }
            } else if (!isDecOrInc(it, index, i, comparing)) return false
        }

        return true
    }

    fun isDecOrInc(variationsOfElem: MutableList<List<Int>>): Boolean = variationsOfElem.any { isDecOrInc(it) }

    fun part2(input: List<String>): Int {
        return input.map { it ->
            var strings = it.split(" ")
            val ints = strings.map { it.toInt() }.toList()
            val shortenedInts = mutableListOf<List<Int>>()

            ints.forEachIndexed { index, i ->
                val elementsLeft = ints.subList(0, index)
                val elementsRight = ints.subList(index + 1, ints.size)

                shortenedInts.add(elementsLeft.plus(elementsRight))
            }
            shortenedInts.add(ints)

            shortenedInts
        }.filter { isDecOrInc(it) }.size
    }


    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
//    part1(input).println()
    part2(input).println()
}

