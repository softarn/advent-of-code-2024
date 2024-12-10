private const val DAY = "09"
private const val DAY_NAME = "Day$DAY"

fun main() {

    fun part1(input: List<String>): Long {
        val line = input.get(0)

        var expanded = mutableListOf<String>()
        var i = 0
        var iv = 0
        for (c in line) {
            if (i % 2 == 1) {
                ".".repeat(c.toString().toInt()).toList().forEach { c -> expanded.add(c.toString())}
            } else {
                for (x in 1..c.toString().toInt()) {
                    expanded.add(iv.toString())
                }
                iv++
            }
            i++
        }

        println(expanded)

        var small = mutableListOf<String>()
        var j = 0
        var endI = expanded.size - 1
        while (expanded[endI] == ".") endI--
        for (c in expanded) {
            if (j > endI) {
                break
            }
            if (c == ".") {
                small.add(expanded[endI])
                endI--
                while (expanded[endI] == ".") endI--
            } else {
                small.add(c)
            }

            j++
        }

        println(small)
        
        val sum  = small.mapIndexed { index, c -> index * c.toLong() }.sum()
        return sum
    }

    fun mergeOnce(expanded: MutableList<MutableList<String>>): Pair<Boolean, MutableList<MutableList<String>>> {
        val merged = mutableListOf<MutableList<String>>()
        var j = 0
        var foundMerge = false

        val reversed = expanded.toMutableList()

        for (cList in reversed) {
            if (cList[0] != ".") {
                var endI = expanded.size - 1
                while (expanded[endI][0] != "." && expanded[endI].size < cList.size) endI--

                if (endI > j) {
                    merged.add(expanded[endI].toMutableList())

                    for (r in 0..< expanded[endI].size) {
                        cList.removeAt(0)
                        expanded[endI][r] = "."
                    }

                    break
                }
            }
            merged.add(cList)
            j++
        }

        merged.addAll(expanded.subList(j, expanded.size).filter { it.isNotEmpty() }.toMutableList())
        return Pair(foundMerge, merged)
    }

    fun part2(input: List<String>): Long {
        val line = input.get(0)

        var expanded = mutableListOf<MutableList<String>>()
        var i = 0
        var iv = 0
        for (c in line) {
            if (i % 2 == 1) {
                val list = mutableListOf<String>()
                for (x in 1..c.toString().toInt()) {
                    list.add(".")
                }
                if (list.isNotEmpty()) {
                    expanded.add(list)
                }
            } else {
                val list = mutableListOf<String>()
                for (x in 1..c.toString().toInt()) {
                    list.add(iv.toString())
                }
                if (list.isNotEmpty()) {
                    expanded.add(list)
                }

                iv++
            }
            i++
        }
        println("step 1")
        println(expanded)

        println("step 2")
        var (_, shuffled) = mergeOnce(expanded)
        println(shuffled)
        var canMergeMore: Boolean
        do {
            var (mergeMore, mutableLists) = mergeOnce(shuffled)

            canMergeMore = mergeMore
            shuffled = mutableLists
            println(shuffled)
        } while (canMergeMore)

        println("step 3")
//        println(expanded)

        val toString = shuffled.joinToString("") { it.joinToString("") }

        val sum = toString.mapIndexed { index, c ->
            if (c == '.') {
                0L
            } else {
                index * c.toString().toLong()
            }
        }.sum()

        return sum
//
//        return 1
    }

// Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("${DAY_NAME}_test")
//    println("test score1: " + part1(testInput))
    println("test score2: " + part2(testInput))
//    check(part2(testInput) == 1)

// Read the input from the `src/Day01.txt` file.
    val input = readInput(DAY_NAME)
//    println("score1: " + part1(input))
//    println("score2: " + part2(input))

}

//val matcher = "mul\\((\\d+),(\\d+)\\)".toRegex()
//        input.forEachIndexed { y, inputX ->
//            inputX.forEachIndexed { x, c ->
//                print("$x, $y, ${c} ")
//            }
//            println()
//        }

