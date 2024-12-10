private const val DAY = "06"
private const val DAY_NAME = "Day$DAY"

fun main() {

    fun turnRight(currChar: Char): Char {
        if (currChar == '^') {
            return 'R'
        }

        if (currChar == 'R') {
            return 'D'
        }

        if (currChar == 'D') {
            return 'L'
        }

        if (currChar == 'L') {
            return '^'
        } else {
            throw Exception("Cant turn")
        }
    }

    fun step(
        currChar: Char,
        currPos: Pair<Int, Int>
    ): Pair<Int, Int> {
        if (currChar == '^') {
            return Pair(currPos.first - 1, currPos.second)
        }

        if (currChar == 'R') {
            return Pair(currPos.first, currPos.second + 1)
        }

        if (currChar == 'D') {
            return Pair(currPos.first + 1, currPos.second)
        }

        if (currChar == 'L') {
            return Pair(currPos.first, currPos.second - 1)
        }

        throw Exception("Can't step")
    }

    fun walk(matrix: MutableList<MutableList<Char>>, currPos: Pair<Int, Int>): Pair<Boolean, Pair<Int,Int>> {
        var currChar = matrix[currPos.first][currPos.second]

        var nextPos: Pair<Int, Int> = step(currChar, currPos)
        var nextChar: Char
        try {
            nextChar = matrix[nextPos.first][nextPos.second]
        } catch (e: IndexOutOfBoundsException) {
            matrix[currPos.first][currPos.second] = 'X'
            return Pair(false, nextPos)
        }

        if (nextChar == '#') {
            matrix[currPos.first][currPos.second] = turnRight(currChar)
            return Pair(true, currPos)
        }

        matrix[nextPos.first][nextPos.second] = matrix[currPos.first][currPos.second]
        matrix[currPos.first][currPos.second] = 'X'

        return Pair(true, nextPos)
    }

    fun walkEntire(
        matrix: MutableList<MutableList<Char>>,
        startPos: Pair<Int, Int>
    ) {
        var currPos = startPos
        val visited = mutableListOf<Triple<Int, Int, Char>>()

        while (true) {
            var currChar = matrix[currPos.first][currPos.second]
            val visit = Triple(currPos.first, currPos.second, currChar)
            if (visited.contains(visit)) {
                throw LoopException()
            }
            visited.add(visit)

            val res = walk(matrix, currPos)

            if (!res.first) {
                break
            }

            currPos = res.second
        }
    }

    fun part1(input: List<String>): Int {
        var matrix = input.map { it.toMutableList() }.toMutableList()

        var startPos = Pair(0,0)
        matrix.forEachIndexed { y, yList ->
            yList.forEachIndexed { x, c ->
                if (c == '^')
                    startPos = Pair(y, x)
            }
        }

        println("StartPos: $startPos")

        var loops = 0
        var currPos = startPos
        while (true) {
            var currChar = matrix[currPos.first][currPos.second]
            var nextPos: Pair<Int, Int> = step(currChar, currPos)
            var nextChar: Char

            try {
                nextChar = matrix[nextPos.first][nextPos.second]
                if (nextChar == '.') {
                    var newMatrix = matrix.map { it.toMutableList() }.toMutableList()
                    newMatrix[nextPos.first][nextPos.second] = '#'
                    try {
                        walkEntire(newMatrix, currPos)
                    } catch (e: LoopException) {
                        loops++
                    }
                }
            } catch (e: IndexOutOfBoundsException) {
            }

            val res = walk(matrix, currPos)

            if (!res.first) {
                break
            }

            currPos = res.second
        }

        println(loops)
        return "X".toRegex().findAll(matrix.map { it.joinToString()  }.joinToString()).count()
    }

    fun part2(input: List<String>): Int {
        return 1
    }

// Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

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

class LoopException: Exception()

//val matcher = "mul\\((\\d+),(\\d+)\\)".toRegex()
//        input.forEachIndexed { y, inputX ->
//            inputX.forEachIndexed { x, c ->
//                print("$x, $y, ${c} ")
//            }
//            println()
//        }

