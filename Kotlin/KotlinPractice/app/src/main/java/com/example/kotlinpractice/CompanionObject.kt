package com.example.kotlinpractice

class Book private constructor(val id: Int, val name: String) {

    companion object BookFactory :IdProvider{

        override fun getId(): Int {
            return 444
        }

        val myBook = "new book"

        fun staticCreate() = Book(0, "animal farm")
    }


}
interface IdProvider {
    fun getId() : Int
}
fun main() {
    val book = Book.staticCreate()

    println("${book.id} , ${book.name}")

    println(Book.getId())
}