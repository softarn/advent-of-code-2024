import java.util.Comparator

private const val DAY = "05"
private const val DAY_NAME = "Day$DAY"

fun main() {

    fun matchesRules(orderingRules: List<Pair<Int, Int>>, order: List<Int>): Boolean {
        var index=0
        for (i in order) {
            for (orderingRule in orderingRules) {
                if (orderingRule.first == i) {
                    val beforeIs = order.subList(0,index)
//                    val afterIs = order.subList(index, order.size - 1)

                    if (beforeIs.contains(orderingRule.second)) {
                        return false
                    }
                }
            }
            index++
        }

        return true
    }

    fun part1(input: List<String>): Int {
        val orderingRulesRegex = "(\\d+?)\\|(\\d+)".toRegex()
        val orderingRules = input
            .takeWhile { orderingRulesRegex.matches(it) }
            .map {
                val all = orderingRulesRegex.find(it)!!
                Pair(all.groupValues[1].toInt(), all.groupValues[2].toInt())
            }

        val orderRegex = "(\\d+?),".toRegex()
        val orders = input
            .filter { orderRegex.containsMatchIn(it) }
            .map { it.split(",") }
            .map { it.map { it.toInt() }.toList() }
            .toList()

        return orders.filter { matchesRules(orderingRules, it) }
            .map { it[((it.size/2))] }
            .sum()
    }

    fun part2(input: List<String>): Int {
        val orderingRulesRegex = "(\\d+?)\\|(\\d+)".toRegex()
        val orderingRules = input
            .takeWhile { orderingRulesRegex.matches(it) }
            .map {
                val all = orderingRulesRegex.find(it)!!
                Pair(all.groupValues[1].toInt(), all.groupValues[2].toInt())
            }

        val orderRegex = "(\\d+?),".toRegex()
        val orders = input
            .filter { orderRegex.containsMatchIn(it) }
            .map { it.split(",") }
            .map { it.map { it.toInt() }.toList() }
            .toList()

        return orders.filter { !matchesRules(orderingRules, it) }
            .map { it.sortedWith(Comping(orderingRules)) }
            .map { it[((it.size/2))] }
            .sum()
    }

// Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

// Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("${DAY_NAME}_test")
    println("test score1: " + part1(testInput))
    println("test score2: " + part2(testInput))
//    check(part2(testInput) == 1)

// Read the input from the `src/Day01.txt` file.
    val input = readInput(DAY_NAME)
    println("score1: " + part1(input))
    println("score2: " + part2(input))

}

class Comping(val orderingRules: List<Pair<Int, Int>>) :
    Comparator<Int> {
    override fun compare(o1: Int?, o2: Int?): Int {
        orderingRules
            .filter { (it.first == o1 || it.first == o2) && (it.second == o1 || it.second == o2)}
            .forEach {
                if (it.first == o1) return 1
                if (it.second == o1) return -1
                return 0
            }

        return 0
    }

}

//val matcher = "mul\\((\\d+),(\\d+)\\)".toRegex()
//        input.forEachIndexed { y, inputX ->
//            inputX.forEachIndexed { x, c ->
//                print("$x, $y, ${c} ")
//            }
//            println()
//        }

