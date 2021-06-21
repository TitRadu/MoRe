import java.io.File
import java.io.UncheckedIOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors
import java.nio.file.NoSuchFileException
import java.nio.file.Paths
import java.util.*
import kotlin.io.path.createTempDirectory

fun parseDirectory(path: String): List<String>? {
    val d = File(path)
    if (!d.exists()) {
        println("File not found!")
        return null

    }
    if (!d.isDirectory) {
        println("First argument isn't a directory!")
        return null

    }

    return d.list().asList()

}

fun moveFilesToExtensionsDirectories(sortDirectoryPath : String, files: List<String>, extensions: MutableList<String>) {
    for (file in files) {
        //println(file)
        val extension = file.substringAfterLast(".")
        if (extension == file) {
            continue

        } else {
            if (!extensions.contains(extension)) {
                extensions.add(extension)
                println(extension)
                val extensionDirectory = File(sortDirectoryPath + "\\" + extension)
                extensionDirectory.mkdir()

            }

            val sourcePath = File(sortDirectoryPath + "\\" + file)
            val targetPath = File(sortDirectoryPath + "\\" + extension + "\\" + file)
            sourcePath.renameTo(targetPath)

        }

    }

}


fun main(args: Array<String>) {
    if (args.size != 1) {
        print("Invalid number of arguments!")
        return

    }
    val directoryName = args[0].substringAfterLast("\\").substringAfterLast("/")
    val files: List<String> = parseDirectory(args[0]) ?: return
    val extensions: MutableList<String> = LinkedList()

    moveFilesToExtensionsDirectories(args[0], files, extensions)

}