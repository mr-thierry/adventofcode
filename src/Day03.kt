fun main() {
    Day03().execute()
}

class Day03 {

    fun execute() {
        val inputTest = readInput("Day03_test")
        val input = readInput("Day03")

        println("Test Solution1:${solution1(inputTest)}")
        println("Solution1:${solution1(input)}")

        println("Test Solution2:${solution2(inputTest)}")
        println("Solution2:${solution2(input)}")
    }

    private fun solution1(input: List<String>): String {
        var total = 0

        input.forEach { str ->
            val compartmentsA = str.substring(0,str.length/2).toCharArray()
            val compartmentsB = str.substring(str.length/2, str.length).toCharArray()

            val duplicates = mutableListOf<Char>()
            compartmentsA.forEach {
                if (compartmentsB.contains(it) && !duplicates.contains(it)) {
                    duplicates.add(it)
                }
            }

            duplicates.forEach { char ->
                total += valueOf(char)
            }
        }

        return "$total"
    }

    private fun solution2(input: List<String>): String {
        var total = 0

        var index = 0
        while (index < input.size) {
            val group1 = input[index]
            val group2 = input[index+1]
            val group3 = input[index+2]

            total += findDuplicate(group1, group2, group3)
            index += 3
        }

        return "$total"
    }

    private fun findDuplicate(group1: String, group2: String, group3: String): Int {
        group1.forEach { char1 ->
            group2.forEach { char2 ->
                if (char1 == char2) {
                    group3.forEach { char3 ->
                        if (char1 == char3) {
                            return valueOf(char1)
                        }
                    }
                }
            }
        }
        error("Not found")
    }

    private fun valueOf(char: Char): Int {
        var value: Int = char.lowercaseChar().code - 'a'.code + 1
        if (char.isUpperCase()) value += 26
        return value
    }
}

