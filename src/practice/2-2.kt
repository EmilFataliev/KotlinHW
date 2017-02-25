package practice

class ShelfConfiguration constructor(val size: String, val booksCapacity: Int){

    override fun toString(): String {
        return "Размер полки: $size\nВмещаемость книг: $booksCapacity"
    }
}

class Shelf constructor(val configuration: ShelfConfiguration){

    var myBooks: MutableList<Book>? = null
    var _booksCapacity = configuration.booksCapacity



    fun addBooks(vararg books: Book){

        if (books.size > _booksCapacity)
            throw IndexOutOfBoundsException("Книжная полка переполнена")

        myBooks = books.toMutableList()

        _booksCapacity -= books.size
    }

    override fun toString(): String {
        return configuration.toString() + "\nВместительность: ${configuration.booksCapacity}. \nКниг на полке: ${configuration.booksCapacity - _booksCapacity} \n${myBooks?.forEach { println(it.toString()) }}"
    }
}

class Bookcase {
    var myShelfs: MutableList<Shelf>

    init {
        /// Create shelfs
        val shelfs: List<Shelf> = listOf(Shelf(ShelfConfiguration("5x5", 5)), Shelf(ShelfConfiguration("2x2", 2)))
        /// Add shelfs in BookCase
        myShelfs = shelfs.toMutableList()
    }


    fun addBooks(vararg books: Book){

        var index: Int = 0
        for (shelf in myShelfs)
        {
            while (shelf._booksCapacity > 0 && index < books.size) {
                shelf.addBooks(books[index])
                ++index
            }
        }
    }


    override fun toString(): String {
        return "Книжных полок: ${myShelfs.size} \n ${myShelfs.forEach { println(it.toString()) }}"
    }
}

fun main(args: Array<String>) {
    val bookCase: Bookcase = Bookcase()
    val bookInfo : BookInformation = BookInformation("Война и мир", "Лев Толстой", "Эксмо, Око", 2004, 928, "Москва")

    bookCase.addBooks(Book(bookInfo))
    println(bookCase.toString())
}

