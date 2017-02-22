package practice

class ShelfConfiguration constructor(val size: String, val booksCapacity: Int){

    override fun toString(): String {
        return "Размер полки: $size\nВмещаемость книг: $booksCapacity"
    }
}

class Shelf constructor(val configuration: ShelfConfiguration){

    var _booksCapacity = configuration.booksCapacity;
    var myBooks: MutableList<Book>? = null


    fun addBooks(vararg books: Book){

        if (books.size > _booksCapacity)
            throw IndexOutOfBoundsException("Книжная полка переполнена")

        myBooks?.addAll(books)

        _booksCapacity -= books.size
    }

    override fun toString(): String {
        return configuration.toString() + "\nВместительность: ${configuration.booksCapacity}. \nКниг на полке: ${configuration.booksCapacity - _booksCapacity} \n${myBooks?.forEach { it.toString() }}"
    }
}

class Bookcase {
    var myShelfs: MutableList<Shelf> = null!!

    init {
        /// Create shelfs
        val shelfs: List<Shelf> = listOf(Shelf(ShelfConfiguration("5x5", 5)), Shelf(ShelfConfiguration("2x2", 2)))
        /// Add shelfs in BookCase
        shelfs.forEach { addShelfs(it) }
    }

    private fun addShelfs(vararg shelfs: Shelf){

        myShelfs.addAll(shelfs)
    }

    fun addBooks(vararg books: Book){

        var index: Int = 0
        for (shelf in myShelfs)
        {
            while (shelf._booksCapacity > 0) {
                shelf.addBooks(books[index])
                ++index
            }
        }
    }


    override fun toString(): String {
        return "Книжных полок: ${myShelfs.size} \n ${myShelfs.forEach { it.toString() }}"
    }
}

fun main(args: Array<String>) {
    val bookCase: Bookcase = Bookcase()
    val bookInfo : BookInformation = BookInformation("Война и мир", "Лев Толстой", "Эксмо, Око", 2004, 928, "Москва")

    bookCase.addBooks(Book(bookInfo))
    println(bookCase.toString())
}

