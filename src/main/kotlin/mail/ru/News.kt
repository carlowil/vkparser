package mail.ru
import kotlinx.serialization.Serializable

@Serializable
data class News(val Id : String, // id �������
                val Text : String, // ����� ������� � ��������� � ��������
                val CountLike : Int, // �����
                val CountViews : Int, // ���������
                val Img : List<String>, // ��������/����������
                val Href : String) { // ������ �� �������
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