package mail.ru
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.charset.Charset
import java.nio.file.StandardOpenOption


fun writeToFileJson(json: String, filename : String) {
    val directoryPath = "jsonSources"
    var file = File(directoryPath)
    if (!file.exists()) {
        file.mkdir()
    }
    file = File(directoryPath, filename)
    file.writeText(json, Charsets.UTF_8)
}

fun readFromFileJson(pathFile : String) : String {
    val file = File(pathFile)
    return file.readLines().joinToString("\n")
}