import mail.ru.*
import org.openqa.selenium.WebDriver
import kotlin.random.*

fun main(args: Array<String>) {
    while (true) {
        printMenu()
        when(readln().trimIndent().lowercase()) {
            "1" -> {
                val driver = initChrome()
                openMail(driver)
                closeBrowser(driver)
            }
            "2" -> {
                val driver = initChrome()
                openMailRegister(driver)
                fillTextField(driver, "fname", "Матвей")
                fillTextField(driver, "lname", "Сизов")
                fillDateField(driver, "/html/body/div[1]/div[3]/div[3]/div[4]/div/div/div/div/form/div[9]/div[2]/div/div/div/div[1]/div/div", "react-select-2-option-${Random.nextInt(0, 28)}")
                fillDateField(driver, "/html/body/div[1]/div[3]/div[3]/div[4]/div/div/div/div/form/div[9]/div[2]/div/div/div/div[3]/div", "react-select-3-option-${Random.nextInt(0, 11)}")
                fillDateField(driver, "/html/body/div[1]/div[3]/div[3]/div[4]/div/div/div/div/form/div[9]/div[2]/div/div/div/div[5]/div/div", "react-select-4-option-${Random.nextInt(0, 110)}")
                fillGenderField(driver, "/html/body/div[1]/div[3]/div[3]/div[4]/div/div/div/div/form/div[12]/div[2]/div/label[${Random.nextInt(1, 3)}]/div[1]")
                closeBrowser(driver)
            }
            "3" -> {
                val driver = initChrome("user-data-dir=C:\\Users\\matve\\AppData\\Local\\Google\\Chrome\\User Data")
                printVkNews(parseVkNews(driver))
                closeBrowser(driver)
            }
            "exit" -> {
                return
            }
            else -> {
                println("Bad input!")
            }
        }
    }
}

fun closeBrowser(driver : WebDriver) {
    println("Print \"close\" - to close browser")
    while(true) {
        if(readln().trimIndent().lowercase() == "close") {
            closeChrome(driver)
            return
        }
        println("Bad input!")
    }
}

fun printMenu() {
    println("Menu:\n" +
            "1 - Open mail.ru with registration page\n" +
            "2 - Open registration page and fill it\n" +
            "3 - Parse vk.com news\n" +
            "exit - Exit from program\n" +
            "Else - Bad input!")
}


fun printVkNews(news : List<News>) {
    for (i in news) {
        println("1. ${i.Id}\n" +
                "2. ${i.Href}\n" +
                "3. ${i.Img.joinToString("\n")}\n" +
                "4. ${i.CountViews}\n" +
                "5. ${i.CountLike}\n" +
                "6. ${i.Text}\n")
    }
}