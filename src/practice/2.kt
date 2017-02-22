package practice;


class BookInformation constructor(val bookName: String, val authorName: String, val publishingHouse: String, val publishingYear: Int, val pageNumber: Int, val city: String = "",  val paperType: String = "", val coverType: String = "", val isbn: String = "", val format: String = ""){

    override fun toString(): String {
        return "\nНазвание: $bookName \nИмя автора: $authorName \nИздательство: $publishingHouse \nГод издания: $publishingYear\nКоличество страниц: $pageNumber \nГород: ${checkInfo(city)} \nТип бумаги: ${checkInfo(paperType)}  \nТип обложки: ${checkInfo(coverType)}  \nISBN: ${checkInfo(isbn)} \nФормат: ${checkInfo(format)}"
    }

    fun checkInfo(checkIT: String) : String{
        if (checkIT.isNullOrEmpty())
            return "-"
        return checkIT
    }
}


open class Book constructor(val bookInfo: BookInformation) {

    override fun toString(): String {
        return bookInfo.toString()
    }
}


fun main(args: Array<String>) {
    val bookInfo : BookInformation = BookInformation("Война и мир", "Лев Толстой", "Эксмо, Око", 2004, 928, "Москва")
    val WarAndPeace : Book = Book(bookInfo)
    println(WarAndPeace.toString())
}