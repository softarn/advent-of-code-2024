private const val DAY = "07"
private const val DAY_NAME = "Day$DAY"

fun main() {

    fun findCalc(first: Int, second: List<String>) {
        TODO("Not yet implemented")
    }

    fun part1(input: List<String>): Int {
        input.map {
            val matchResult = "(\\d+): ([\\d\\w]+)$".toRegex().find(it)!!
            Pair(matchResult.groupValues[1].toInt(), matchResult.groupValues[2].split(" ").toList())
        }.forEach {
            findCalc(it.first, it.second)
        }
        return 1
    }

    fun part2(input: List<String>): Int {
        return 1
    }

// Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("${DAY_NAME}_test")
    println("test score1: " + part1(testInput))
//    println("test score2: " + part2(testInput))
//    check(part2(testInput) == 1)

// Read the input from the `src/Day01.txt` file.
    val input = readInput(DAY_NAME)
    println("score1: " + part1(input))
//    println("score2: " + part2(input))

}

//val matcher = "mul\\((\\d+),(\\d+)\\)".toRegex()
//        input.forEachIndexed { y, inputX ->
//            inputX.forEachIndexed { x, c ->
//                print("$x, $y, ${c} ")
//            }
//            println()
//        }

