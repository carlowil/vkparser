package mail.ru

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import kotlinx.coroutines.*
import org.openqa.selenium.WebElement
import kotlin.random.*

fun initChrome(strOption: String): WebDriver {
    val options = ChromeOptions()
    WebDriverManager.chromedriver().setup()
    if (strOption.isNullOrEmpty()) {
        return ChromeDriver(options)
    }
    options.addArguments(strOption)
    return ChromeDriver(options)
}

fun initChrome() : WebDriver {
    val options = ChromeOptions()
    WebDriverManager.chromedriver().setup()
    return ChromeDriver(options)
}

fun closeChrome(driver: WebDriver) {
    driver.quit()
}

fun openPage(page: String, driver : WebDriver){
    driver.navigate().to(page)
}

fun openMail(driver: WebDriver) {
    openPage("https://mail.ru/", driver)
    val elements = driver.findElements(By.tagName("a")).toList()
    for (i in elements) {
        if (i.isDisplayed && i.text.trim().lowercase() == "создать почту") {
            i.click()
            break
        }
    }
}

fun openMailRegister(driver: WebDriver) {
    openPage("https://account.mail.ru/signup?from=main&rf=auth.mail.ru&app_id_mytracker=58519", driver)
}

fun fillTextField(driver: WebDriver, id : String, text : String ) = runBlocking {
    val elements = driver.findElements(By.id(id)).toList()
    launch {
        for(i in elements) {
            if (i.isDisplayed) {
                for(j in text) {
                    delay(Random.nextLong(100, 201))
                    i.sendKeys(j.toString())
                }
            }
        }
    }
}

fun fillDateField(driver: WebDriver, xpath: String, id: String) {
    var elements = driver.findElements(By.xpath(xpath)).toList()
    for (i in elements) {
        if(i.isDisplayed) {
            i.click()
        }
    }
    elements = driver.findElements(By.id(id)).toList()
    for (i in elements) {
        if (i.isDisplayed) {
            i.click()
        }
    }
}

fun fillGenderField(driver : WebDriver, xpath: String) {
    driver.findElement(By.xpath(xpath)).click()
}

fun parseVkNews(driver : WebDriver) : List<News>{
    driver.get("https://vk.com/feed")

    val vknews = mutableListOf<News>()

    val feedPosts = mutableListOf<WebElement>()
    val feedRowElements = driver.findElement(By.id("feed_rows"))
    feedRowElements.findElements(By.className("feed_row")).forEach { feedPosts.add(it.findElement(By.tagName("div"))) }
    for(i in feedPosts) {
        if(i.isDisplayed && !i.getAttribute("data-post-id").isNullOrEmpty()) {

            val id = i.getAttribute("id").toString()
            val img = mutableListOf<String>()
            val href = i.findElement(By.className("post_link")).getAttribute("href").toString()
            val countLike = findLikes(i.findElement(By.className("like_btns")).text.toString())
            var countViews = 0
            var text = ""

            try {
                countViews = findViews(i.findElement(By.className("like_views")).getAttribute("title").toString())
            } catch (_: Exception) {

            }

            try {
                for(j in i.findElement(By.className("MediaGridContainerWeb--post")).findElements(By.tagName("img"))) {
                    img.add(j.getAttribute("src").toString())
                }
            } catch (_: Exception) {

            }

            try {
                text = i.findElement(By.className("wall_post_text")).text.toString()
            } catch (_: Exception) {

            }
            vknews.add(News(id, text, countLike, countViews, img, href))
        }
    }
    return vknews.toList()
}

private fun findLikes(likeBtns : String): Int {
    var i = 0
    var result = ""
    while(likeBtns[i] != '\n' && likeBtns[i].digitToIntOrNull() != null) {
        result += likeBtns[i]
        i++
    }
    return if (result.toIntOrNull() == null) 0 else result.toInt()
}

private fun findViews(views : String) : Int {
    var i = 0
    var result = ""
    while(views[i] != ' ') {
        result += views[i]
        ++i
    }
    return result.toInt()
}


fun jsonOne(news : List<News>) : List<Json1>{
    val json1 = mutableListOf<Json1>()
    news.forEach { json1.add(Json1(it.Id, it.Text)) }
    return json1.toList()
}


fun jsonTwo(news : List<News>) : List<Json2>{
    val json2 = mutableListOf<Json2>()
    news.forEach { json2.add(Json2(it.Id, it.Img)) }
    return json2.toList()
}


fun jsonThree(news : List<News>) : List<Json3> {
    val json3 = mutableListOf<Json3>()
    news.forEach { json3.add(Json3(it.Id, it.Href)) }
    return json3.toList()
}
