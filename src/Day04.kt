private const val DAY = "04"
private const val DAY_NAME = "Day$DAY"

fun main() {

    fun part1(input: List<String>): Int {
        val y = mutableListOf<List<Char>>()
        input.forEachIndexed { indexX, s ->
            y.add(s.toList())
        }

        var total = 0
        y.forEachIndexed { indexY, charsX ->
            charsX.forEachIndexed { indexX, charsY ->

                if (indexX + 3 < y[indexY].size) {
                    val horizontalWord =
                        "".plus(y[indexY][indexX]).plus(y[indexY][indexX + 1]).plus(y[indexY][indexX + 2])
                            .plus(y[indexY][indexX + 3])
                    println(horizontalWord)

                    if (horizontalWord == "XMAS") {
                        total++
                    }

                    if (horizontalWord == "SAMX") {
                        total++
                    }
                }

                if (indexY + 3 < y.size) {
                    val verticalWord =
                        "".plus(y[indexY][indexX]).plus(y[indexY + 1][indexX]).plus(y[indexY + 2][indexX])
                            .plus(y[indexY + 3][indexX])
                    println(verticalWord)

                    if (verticalWord == "XMAS") {
                        total++
                    }

                    if (verticalWord == "SAMX") {
                        total++
                    }

                }

                if (indexY + 3 < y.size && indexX + 3 < y[indexY].size) {
                    val diagonalRight =
                        "".plus(y[indexY][indexX]).plus(y[indexY + 1][indexX + 1]).plus(y[indexY + 2][indexX + 2])
                            .plus(y[indexY + 3][indexX + 3])
                    println(diagonalRight)

                    if (diagonalRight == "XMAS") {
                        total++
                    }

                    if (diagonalRight == "SAMX") {
                        total++
                    }
                }

                if (indexY + 3 < y.size && indexX - 3 >= 0) {
                    val diagonalLeft =
                        "".plus(y[indexY][indexX]).plus(y[indexY + 1][indexX - 1]).plus(y[indexY + 2][indexX - 2])
                            .plus(y[indexY + 3][indexX - 3])
                    println(diagonalLeft)

                    if (diagonalLeft == "XMAS") {
                        total++
                    }

                    if (diagonalLeft == "SAMX") {
                        total++
                    }
                }
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        val y = mutableListOf<List<Char>>()
        input.forEachIndexed { indexX, s ->
            y.add(s.toList())
        }

        var total = 0
        y.forEachIndexed { indexY, charsX ->
            charsX.forEachIndexed { indexX, charsY ->
//                try {
//                    val verticalWord =
//                        "".plus(y[indexY - 1][indexX]).plus(y[indexY][indexX]).plus(y[indexY + 1][indexX])
//                    println(verticalWord)
//                    val horizontalWord =
//                        "".plus(y[indexY][indexX - 1]).plus(y[indexY][indexX]).plus(y[indexY][indexX + 1])
//                    println(horizontalWord)
//
//                    if (verticalWord == "MAS") {
//                        if (horizontalWord == "MAS") {
//                            total++
//                        }
//
//                        if (horizontalWord == "SAM") {
//                            total++
//                        }
//                    }
//
//                    if (verticalWord == "SAM") {
//                        if (horizontalWord == "MAS") {
//                            total++
//                        }
//
//                        if (horizontalWord == "SAM") {
//                            total++
//                        }
//                    }
//                } catch (e: Exception ) {
//                }


                try {
                    val diagonalLeft =
                        "".plus(y[indexY - 1][indexX + 1]).plus(y[indexY][indexX]).plus(y[indexY + 1][indexX - 1])
                    println(diagonalLeft)

                    val diagonalRight =
                        "".plus(y[indexY - 1][indexX - 1]).plus(y[indexY][indexX]).plus(y[indexY + 1][indexX + 1])
                    println(diagonalRight)

                    if (diagonalRight == "MAS") {
                        if (diagonalLeft == "MAS") {
                            total++
                        }

                        if (diagonalLeft == "SAM") {
                            total++
                        }
                    }

                    if (diagonalRight == "SAM") {
                        if (diagonalLeft == "MAS") {
                            total++
                        }

                        if (diagonalLeft == "SAM") {
                            total++
                        }
                    }
                } catch (e: Exception) {}
        }
    }
    return total
}

// Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

// Or read a large test input from the `src/Day01_test.txt` file:
val testInput = readInput("${DAY_NAME}_test")
//println("score " + part2(testInput))
    check(part2(testInput) == 9)

// Read the input from the `src/Day01.txt` file.
val input = readInput(DAY_NAME)
//    part1(input).println()
    part2(input).println()

}

//val matcher = "mul\\((\\d+),(\\d+)\\)".toRegex()
