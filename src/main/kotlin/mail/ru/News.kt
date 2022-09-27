package mail.ru
import kotlinx.serialization.Serializable

@Serializable
data class News(val Id : String, // id новости
                val Text : String, // текст новости с хештегами и ссылками
                val CountLike : Int, // лайки
                val CountViews : Int, // просмотры
                val Img : List<String>, // картинки/фотографии
                val Href : String) { // ссылка на новость
//    override fun toString(): String {
//        return "1. $Id\n2. $Text\n3. $CountLike\n4. $CountViews\n5. $Img\n6. $Href"
//    }
}

@Serializable
data class Json1 (val Id : String, val Text : String)

@Serializable
data class Json2 (val Id : String, val Img : List<String>)

@Serializable
data class Json3 (val Id : String, val Href : String)