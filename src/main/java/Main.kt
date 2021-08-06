import java.io.File
import java.util.*

class Platform{
    companion object {
        lateinit var platformDelimiter: String

    }

}

class Extension(var name: String) {
    var count: Int = 1

}

fun checkExistence(path: String): Boolean {
    val d = File(path)
    if (!d.exists()) {
        println("File not found!")
        return false

    }
    if (!d.isDirectory) {
        println("First argument isn't a directory!")
        return false

    }

    return true


}

fun obtainFiles(directoryPath: String): List<String>{
    val files = File(directoryPath).list()
    val filesWithoutDirectoriesList: MutableList<String> = LinkedList()
    for (file in files){
        val f = File(directoryPath + Platform.platformDelimiter + file)
        if(!f.isDirectory){
            filesWithoutDirectoriesList.add(file)

        }

    }

    return filesWithoutDirectoriesList

}

fun obtainExtensions(directoryPath: String): List<Extension> {
    val files: List<String> = obtainFiles(directoryPath)
    val extensionsNames: MutableList<String> = LinkedList()
    val extensions: MutableList<Extension> = LinkedList()
    for (file in files) {
        val extensionName = file.substringAfterLast(".")
        if (extensionName == file) {
            continue

        } else {
            if (!extensionsNames.contains(extensionName)) {
                extensionsNames.add(extensionName)
                extensions.add(Extension(extensionName))

            }

        }

    }

    return extensions

}

fun renameFiles(directoryPath: String, extensions: List<Extension>) {
    val files: List<String> = obtainFiles(directoryPath)
    for (file in files) {
        val extensionName = file.substringAfterLast(".")
        if (extensionName == file) {
            continue

        }
        val extension = getExtensionByName(extensionName, extensions) ?: return
        val oldFilePath = File(directoryPath + Platform.platformDelimiter  + file)
        val newFilePath = File(directoryPath + Platform.platformDelimiter  + "file" + extension.count + "." + extensionName)
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
    val files: List<String> = obtainFiles(directoryPath)
    for(ext in extensions){
        val extensionDirectory = File(directoryPath + Platform.platformDelimiter  + ext.name)
        extensionDirectory.mkdir()

    }

    for (file in files) {
        val extensionName = file.substringAfterLast(".")
        if (extensionName == file) {
            continue
        }
        val sourcePath = File(directoryPath + Platform.platformDelimiter  + file)
        val targetPath = File(directoryPath + Platform.platformDelimiter + extensionName + Platform.platformDelimiter + file)
        sourcePath.renameTo(targetPath)

    }

}

fun selectPlatformDelimiter(directoryPath: String){
    if(directoryPath.contains("\\") || directoryPath.contains(":")){
        Platform.platformDelimiter = "\\"

    }
    if(directoryPath.contains("/")){
        Platform.platformDelimiter = "/"

    }

}


fun main(args: Array<String>) {
    if (args.isEmpty() || args.size > 2) {
        println("Invalid number of arguments!")
        return

    }

    if(args.size == 2 && (args[1]!="-r" && args[1]!= "-m")){
        println("Invalid option!")
        return

    }

    if(!checkExistence(args[0])){
        return

    }

    selectPlatformDelimiter(args[0])

    val extensions: List<Extension> = obtainExtensions(args[0])

    if(args.size == 1 || (args.size == 2 && args[1] == "-r")){
        renameFiles(args[0], extensions)

    }
    if(args.size == 1 || (args.size == 2 && args[1] == "-m")) {
        moveFilesToExtensionsDirectories(args[0], extensions)

    }

}