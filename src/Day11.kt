import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day11 {

    private val day = "Day11"

    @Test
    fun testSolution1() {
        assertThat(solution1(readInput("${day}_test"))).isEqualTo(10605)
    }

    @Test
    fun solution1() {
        assertThat(solution1(readInput(day))).isEqualTo(120384)
    }

    @Test
    fun testSolution2() {
        assertThat(solution2(readInput("${day}_test"))).isEqualTo(2713310158)
    }

    @Test
    fun solution2() {
        assertThat(solution2(readInput(day))).isEqualTo(32059801242)
    }

    private fun solution1(input: List<String>): Long {
        return execute(input, roundCount = 20, true)
    }

    private fun solution2(input: List<String>): Long {
        return execute(input, roundCount = 10000, false)
    }

    private fun execute(input: List<String>, roundCount: Int, divideWorry: Boolean): Long {
        val monkeys = readMonkeys(input)

        for (i in 0 until roundCount) {
            println("Round $i ------------------")
            doRound(monkeys, divideWorry)
        }


        val topTwoMonkeys = monkeys.sortedBy { it.inspectionCount }.takeLast(2)
        return topTwoMonkeys[0].inspectionCount * topTwoMonkeys[1].inspectionCount
    }

    private fun doRound(monkeys: List<Monkey>, divideWorry: Boolean) {
        val lcm = monkeys.map { it.divBy }.reduce { a, b -> a * b }

        monkeys.forEach { monkey ->

            monkey.inspectionCount = monkey.inspectionCount + monkey.items.size
            monkey.items.forEach { item ->
                var newWorry = monkey.operation(item)

                if (divideWorry) {
                    newWorry /= 3L
                } else {
                    newWorry %= lcm
                }

                val result = newWorry % monkey.divBy == 0.toLong()
                val targetMonkey = monkey.target(result, monkeys)
                targetMonkey.items.add(newWorry)

            }
            monkey.items.clear()
        }
    }

    private fun readMonkeys(input: List<String>): List<Monkey> {
        val result = mutableListOf<Monkey>()

        val iterator = input.iterator()
        while (iterator.hasNext()) {
            val name = iterator.next()

            if (name.isNotEmpty()) {
                val startingItems = iterator.next().content()
                val operation = iterator.next().content()
                val test = iterator.next().content()
                val ifTrue = iterator.next().content()
                val ifFalse = iterator.next().content()

                val items = startingItems.split(",").map { it.trim().toLong() }.toMutableList()

                val operator = operation.substring("new = old ".length, "new = old ".length + 1)
                val operationValue = operation.substring("new = old *".length).trim()

                val mod = test.substring("divisible by ".length).toLong()
                val targetIfTrue = ifTrue.substring("throw to monkey ".length).toInt()
                val targetIfFalse = ifFalse.substring("throw to monkey ".length).toInt()

                val monkey = object : Monkey(
                    name = name.substring(0, name.length - 1),
                    items = items,
                    divBy = mod
                ) {
                    override fun operation(old: Long): Long {
                        return if (operationValue == "old") {
                            when (operator) {
                                "*" -> old * old
                                "+" -> old + old
                                else -> TODO("operator unknown:$operator")
                            }
                        } else {
                            when (operator) {
                                "*" -> old * operationValue.toLong()
                                "+" -> old + operationValue.toLong()
                                else -> TODO("operator unknown:$operator")
                            }
                        }
                    }

                    override fun target(modResult: Boolean, monkeys: List<Monkey>): Monkey {
                        return if (modResult) {
                            monkeys[targetIfTrue]
                        } else {
                            monkeys[targetIfFalse]
                        }
                    }
                }

                result.add(monkey)
            }
        }

        return result
    }

    abstract class Monkey(
        val name: String,
        val items: MutableList<Long>,
        var inspectionCount: Long = 0,
        val divBy: Long,
    ) {

        abstract fun operation(old: Long): Long
        abstract fun target(modResult: Boolean, monkeys: List<Monkey>): Monkey

    }

    private fun String.content() = substring(indexOf(":") + 1, length).trim()

}





