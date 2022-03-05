package com.example.kotlinpractice

data class Ticket(val companyName: String, val name: String, var date: String, var city: String)

class TicketNormal(val companyName: String, val name: String, var date: String, var city: String)
// toString(), hashCode(), equals(), copy()


fun main() {
    val ticketA = Ticket("KoreanAir", "ree", "2022-02-22", "seoul")
    val ticketB = TicketNormal("KoreanAir", "ree", "2022-02-22", "seoul")


    println(ticketA) // Ticket(companyName=KoreanAir, name=ree, date=2022-02-22, city=seoul)
    println(ticketB) // 객체 주소값 출력 com.example.kotlinpractice.TicketNormal@5b1d2887
}