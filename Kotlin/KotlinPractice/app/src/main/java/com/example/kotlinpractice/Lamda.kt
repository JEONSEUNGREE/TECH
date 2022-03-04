package com.example.kotlinpractice

// 1. Lamda
// 람다식은 우리가 마치 value 처럼 다룰 수 있는 익명함수이다.
// 1) 메소드의 파라미터로 넘겨줄수가 있다. fun maxBy(a : Int)
// 2) return 값으로 사용 할 수가 있다.

// 1. 람다의 기본정의
// val lamdaName : Type = { argumentList -> codeBody }

val square = {number : Int -> number * number}
val square2 : (Int) -> (Int) ={number : Int -> number * number}

val nameAge = {name : String, age : Int ->
    "my name is ${name} I'm ${age}"
}

// 확장함수
val pizzaIsGreat : String.() -> String = {
    this + "Pizza is the best!"
}

fun extendString(name : String, age : Int) : String {

    val introduceMyself : String.(Int) -> String = { "I am ${this} and ${it} years old"}
//    val introduceMyself : String.(Int) -> String = { year : Int -> "I am ${this} and ${year} years old"}

    return name.introduceMyself(age)
}

val calculate : (Int) -> (String) = {
    when(it) {
        in 0..40 -> "fail"
        in 41..70 -> "pass"
        in 71..100 -> "perfect"
        else -> { "error"}
    }
}

// 람다를 표현하는 여러가지 방법

fun invokeLamda(lamda : (Double) -> Boolean) : Boolean {
    return lamda(5.234)
}

fun main() {
    println(square(5))
    println(nameAge("ree",99))
    val a = "ree said"
    val b = "mac said"
    println(a.pizzaIsGreat())
    println(b.pizzaIsGreat())

    println(extendString("ree", 28))

    println(calculate(50))

    val lamda = {number : Double ->
        number == 4.3213
    }
    println(invokeLamda(lamda)) //false

    println(invokeLamda({true}))
    println(invokeLamda({it > 3.22}))

//    두가지 표현식 (중괄호를 바로사용해서 생략가능)
    invokeLamda(lamda)
    invokeLamda { it > 3.22 }

}