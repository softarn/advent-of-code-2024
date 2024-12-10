import kotlin.io.path.fileVisitor

private const val DAY = "10"
private const val DAY_NAME = "Day$DAY"

fun main() {

    val dirs = arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))

    fun getFrom(matrix: List<List<Int>>, newCoord: Pair<Int, Int>): Int {
        return matrix[newCoord.first][newCoord.second]
    }

    fun countPaths(coords: Pair<Int, Int>, lastDigit: Int, matrix: List<List<Int>>, visited: MutableList<Pair<Int, Int>>): Int {
        println(lastDigit)

//        visited.add(coords)
        if (lastDigit == 9) { return 1 }

        var paths = 0

        dirs.forEach {
            try {
                val newCoord = Pair(it.first + coords.first, it.second + coords.second)
                val nextDigit = getFrom(matrix, newCoord)
                if (lastDigit + 1 == nextDigit && !visited.contains(newCoord)) {
                    paths += countPaths(newCoord, nextDigit, matrix, visited)
                }
            } catch (e: IndexOutOfBoundsException) { }
        }

        return paths
    }

    fun countPaths(coords: Pair<Int, Int>, lastDigit: Int, matrix: List<List<Int>>): Int {
        return countPaths(coords, lastDigit, matrix, mutableListOf())
    }

    fun part1(input: List<String>): Int {
        var matrix = input.map { it -> it.map { it.toString().toInt() }.toList() }.toList()

        val pathsCount = matrix.mapIndexed { y, value ->
            value.mapIndexed { x, dig ->
                if (dig == 0) {
                    val countPaths = countPaths(Pair(y, x), dig, matrix)
                    println("For start $y, $x: $countPaths")
                    countPaths
                } else {
                    0
                }
            }.sum()
        }.sum()

        return pathsCount
    }

    fun part2(input: List<String>): Long {
        return 1
    }

// Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("${DAY_NAME}_test")
//    println("test score1: " + part1(testInput))
//    println("test score2: " + part2(testInput))
//    check(part2(testInput) == 1)

// Read the input from the `src/Day01.txt` file.
    val input = readInput(DAY_NAME)
    println("score1: " + part1(input))
//    println("score2: " + part2(input))

}

//val matcher = "mul\\((\\d+),(\\d+)\\)".toRegex()

