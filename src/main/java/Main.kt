import java.io.File
import java.io.UncheckedIOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors
import java.nio.file.NoSuchFileException
import java.nio.file.Paths
import java.util.*
import kotlin.io.path.createTempDirectory

class Extension(name: String) {
    var name: String = name
    var count: Int = 1

}

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

fun obtainExtensions(directoryPath: String): List<Extension>? {
    val files: List<String> = parseDirectory(directoryPath) ?: return null
    val extensions: MutableList<Extension> = LinkedList()
    for (file in files) {
        val extensionName = file.substringAfterLast(".")
        if (extensionName == file) {
            continue

        } else {
            if (!extensions.contains(extensionName)) {
                extensions.add(Extension(extensionName))

            }

        }

    }

    return extensions
}

fun renameFiles(directoryPath: String, extensions: List<Extension>) {
    val files: List<String> = parseDirectory(directoryPath) ?: return
    for (file in files) {
        val extensionName = file.substringAfterLast(".")
        if (extensionName == file) {
            continue

        }
        val extension = getExtensionByName(extensionName, extensions) ?: return
        val oldFilePath = File(directoryPath + "\\" + file)
        val newFilePath = File(directoryPath + "\\" + "file" + extension.count + "." + extensionName)
        oldFilePath.renameTo(newFilePath)
        extension.count++

    }

}

fun getExtensionByName(extensionName: String, extensions: List<Extension>): Extension? {
    for (ext in extensions) {
        if (ext.name == extensionName)
            return ext

    }

    return null
}

fun moveFilesToExtensionsDirectories(directoryPath: String, extensions: List<Extension>) {
    val files: List<String> = parseDirectory(directoryPath) ?: return
    for(ext in extensions){
        val extensionDirectory = File(directoryPath + "\\" + ext.name)
        extensionDirectory.mkdir()

    }

    for (file in files) {
        val extensionName = file.substringAfterLast(".")
        if (extensionName == file) {
            continue
        }
        val sourcePath = File(directoryPath + "\\" + file)
        val targetPath = File(directoryPath + "\\" + extensionName + "\\" + file)
        sourcePath.renameTo(targetPath)

    }

}


fun main(args: Array<String>) {
    if (args.isEmpty() || args.size > 2) {
        print("Invalid number of arguments!")
        return

    }

    if(args.size == 2 && (args[1]!="-r" && args[1]!= "-m")){
        print("Invalid option!")
        return

    }

    val extensions: List<Extension> = obtainExtensions(args[0]) ?: return

    if(args.size == 1 || (args.size == 2 && args[1] == "-r")){
        renameFiles(args[0], extensions)

    }
    if(args.size == 1 || (args.size == 2 && args[1] == "-m")) {
        moveFilesToExtensionsDirectories(args[0], extensions)

    }

}