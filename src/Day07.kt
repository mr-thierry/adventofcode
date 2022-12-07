import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day07 {

    private val day = "Day07"

    @Test
    fun testSolution1() {
        assertThat(solution1(readInput("${day}_test"))).isEqualTo(95437)
    }

    @Test
    fun solution1() {
        assertThat(solution1(readInput(day))).isEqualTo(1307902)
    }

    @Test
    fun testSolution2() {
        assertThat(solution2(readInput("${day}_test"))).isEqualTo(24933642)
    }

    @Test
    fun solution2() {
        assertThat(solution2(readInput(day))).isEqualTo(7068748)
    }

    private fun solution1(input: List<String>): Int {
        val root = buildFileTree(input)
        return root.directories()
            .filter { it.totalSize < 100000 }
            .sumOf { it.totalSize }
    }

    private fun solution2(input: List<String>): Int {
        val root = buildFileTree(input)

        val totalSpace = 70000000
        val usedSpace = root.totalSize
        val freeSpace = totalSpace - usedSpace
        val totalSpaceToFree = 30000000 - freeSpace

        return root.directories()
            .sortedBy { it.totalSize }
            .first { it.totalSize > totalSpaceToFree }
            .totalSize

    }

    private fun buildFileTree(input: List<String>): Directory {
        val root = Directory("/", null)
        var currentDir = root

        val iterator = input.listIterator()
        while (iterator.hasNext()) {
            val cmd = parseCommand(iterator.next())
            if (cmd is Command.ChangeDir) {
                currentDir = changeDirectory(cmd, currentDir, root)
            } else if (cmd is Command.ListFiles) {
                addFiles(iterator, currentDir)
            }
        }
        return root
    }

    private fun addFiles(iterator: ListIterator<String>, currentDir: Directory) {
        while (iterator.hasNext()) {
            val value = iterator.next()
            if (value.startsWith("$")) {
                iterator.previous()
                return
            } else {
                val values = value.split(" ")
                if (values[0] == "dir") {
                    currentDir.directories.add(Directory(values[1], currentDir))
                } else {
                    currentDir.files.add(File(values[1], values[0].toInt()))
                }
            }
        }
    }


    private fun changeDirectory(cmd: Command.ChangeDir, currentDir: Directory, root: Directory): Directory {
        return when (cmd.destination) {
            "/" -> root
            ".." -> currentDir.parent!!
            else -> {
                if (currentDir.directories.none { directory -> directory.name == cmd.destination }) {
                    currentDir.directories.add(Directory(name = cmd.destination, parent = currentDir))
                }
                currentDir.directories.first { directory -> directory.name == cmd.destination }
            }
        }
    }

    private fun parseCommand(command: String): Command {
        val elements = command.split(" ")

        return if (elements[1] == "cd") {
            Command.ChangeDir(elements[2])
        } else {
            Command.ListFiles
        }
    }

}

sealed interface Command {
    class ChangeDir(val destination: String) : Command
    object ListFiles : Command
}

class Directory(
    val name: String,
    val parent: Directory?,
    val files: MutableList<File> = mutableListOf(),
    val directories: MutableList<Directory> = mutableListOf()
) {

    val totalSize: Int
        get() = files.sumOf { it.size } + directories.sumOf { it.totalSize }

    fun directories(): List<Directory> {
        return directories.toMutableList().apply {
            directories.forEach { addAll(it.directories()) }
        }
    }
}


data class File(val name: String, val size: Int)



