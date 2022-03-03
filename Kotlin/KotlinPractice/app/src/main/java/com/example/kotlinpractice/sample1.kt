package com.example.kotlinpractice

fun main() {
//    helloWorld()

//    println(add( 4, 4))

//  3. string Template
//    val name = "ree"
//    println("my name is ${name}")
//   이스케이프표시로 $를 표시할 수 있다.
//    println("this is 2\$a")

//    checkNum(90)
//    forAndWhile()

//    nullcheck()
    ignoreNulls(null)

}

// 1. 함수
// 자바의 경우 void 혹은 특정 데이터 타입을 적었지만 kotlin은 : Unit 혹은 생략을 하게된다.
fun helloWorld() : Unit {
    println("Hello World!")
}
// 변수 타입을 뒤에 작성한다.
fun add(a : Int, b : Int) : Int {
    return a+b
}

// 2. value vs var
// val = value 변하지않는 값
// var = 변할 수 있는값

fun hi() {
    val a : Int = 10

    var b : Int = 9

    b = 100

    val c = 100
    var d = 100

    var name = "ree"

}

// 4. 조건식

fun maxBy(a : Int, b : Int) : Int {

    if( a > b) {
        return a
    } else {
        return b
    }
}

// 삼항 연산자가 없기때문에 아래와 같이 사용함
fun maxBy2(a : Int, b : Int) = if(a>b) a else b

fun checkNum(score : Int) {
    when(score) {
        0 -> println("this is 0")
        1 -> println("this is 1")
        2,3 -> println("this is 2 or 3")
        else -> println("I don't know")
    }

    var b : Int = when(score) {
        1-> 1
        2-> 2
        else -> 3
    }

    println("b : ${b}")
    when(score) {
        in 90..100 -> println("You are genius")
        in 10..80 -> println("not bad")
        else -> println("okay")
    }
}

// Expression vs Statement

// 5. Array and List

// Array
// List 1. List 2. MutableList(수정불가)

fun array() {
    val array = arrayOf(1,2,3,)
    val list = listOf(1,2,3)

    val array2 = arrayOf(1,"d", 3.4f)
    val list2 = listOf(1,"d", 11L)

    array[0] = 3
//    list의 경우 인터페이스로 특정 메서드를 통해 사용한다.
    val result = list.get(0)

    val arrayList = arrayListOf<Int>()
    arrayList.add(10)
    arrayList.add(20)
    arrayList[0] = 20

}

// 6. For / While

fun forAndWhile() {

    val student = arrayListOf("joyce","ree","jenny","mimi")

    for (name in student) {
        println("${name}")
    }

    var sum : Int = 0
//    for ( i in 1..10) {
//        println(i)
//        sum += i
//        println(sum)
//    }

//    for (i in 1 until 100) {
//    until은 마지막 숫자 포함 안함
   for (i in 1..100) {
        sum += i
    }
    println(sum)

    var index = 0
    while(index <= 10) {
        println("current index ; ${index}")
        index++
    }
//    index도 함께 출력하기
    for ( (index , name) in student.withIndex()) {
        println("index : ${index + 1}, name : ${name}")
    }
}

// 7. Nullable / NonNull

fun nullcheck() {
    //NRE : Null pointer Exception

    var name = "ree"

    var nullName : String? = null

    var nameInUpperCase = name.toUpperCase()
//    nullname이 null이아니면 uppercase로 두고 아니면 null로 반환
//    컴파일시점에서 null체크
    var nullNameInUpper = nullName?.toUpperCase()

//    ?:
    val lastNameA = null
    val lastName : String? = lastNameA

    val fullName = name + (lastName?: " No lastName")

    println(fullName)

}

fun ignoreNulls(str : String?) {
//    null이 아닌경우 확실하게 !! 표시로 알려줄수있다.
//    val mNotNull : String = str!!
//    val upper = mNotNull.toUpperCase()

//    email이 null이 아닌경우 람다식 내부에 코드를 실행한다.
    val info = str
    val email : String? = info

    email?.let{
        println("is not Null")
    }

}