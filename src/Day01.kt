fun main() {
    val input = readInput("Day01_test")

    val list = mutableListOf<List<Int>>()

    var current = mutableListOf<Int>()
    input.forEach {
        if (it.isEmpty()) {
            list.add(current)

            current = mutableListOf<Int>()
        } else {
            current.add(it.toInt())
        }
    }


    val result1 = list.map { item -> item.sumOf { it } }.maxOf { it }
    val result2 = list.map { item -> item.sumOf { it } }.sorted().takeLast(3).sum()

    println("Result 1:$result1")
    println("Result 2:$result2")

}
