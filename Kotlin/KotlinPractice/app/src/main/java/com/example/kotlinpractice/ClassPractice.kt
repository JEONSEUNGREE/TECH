package com.example.kotlinpractice

// 자바와 다르게 클래스명과 파일이름이 일치할 필요가 없고 여러 클래스를 한 파일에 넣을 수 있다.

class Human constructor(val name : String){
//    생성자의 경우 자바와 다르게 constructor로 위와같이 선언한다. constructor 생략가능
//    위 코드블록 변수를 사용하기 위해서는 init에 넣어둔다.

    constructor(name: String, age: Int) : this(name) {
        println("my name is ${name}, ${age}years old")
    }
//    주 생성자 제일 먼저 실행됨 (생성자가 여러개여도)
    init {
        println("생성자 init")
    }

//    val name = "ree"

    fun eatingCake() {
        println("this is good")

    }
}

fun main() {
//    인스턴스 생성을 new 없이 클래스명() 이런식으로 선언가능
    val human = Human("ree")

    val stranger = Human("stranger")

    human.eatingCake()

    val test = Human("테스팅",15)
    println("this human's name is ${human.name}")
}