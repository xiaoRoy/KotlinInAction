package com.learn.kotlininaction.chap5.lambdaexpression

/*
* 5.2 Functional APIs for collections
* */

/*
* 5.2.1 Essentials: filter and map
* */

private val numbers = listOf(1, 2, 3, 4, 5)
private val people = listOf(Person("Mary", 22), Person("Smith", 18), Person("Rose", 32), Person("Lily", 12))
private val isAdultPredicate = {person: Person -> person.age >= 18 }

fun filterEven(){
    val evenPredicate: (Int) -> Boolean = {number: Int -> number % 2 == 0}
    /*
    * This name is generated if the context expects a lambda with only one argument, and its type can be inferred
    * */
    val evenPredicateA: (Int) -> Boolean = { it % 2 == 0 }
    numbers.filter (evenPredicate)
    numbers.filter(){number: Int -> number % 2 == 0}
    numbers.filter {number: Int -> number % 2 == 0}
    numbers.filter { number -> number % 2 == 0 }
    numbers.filter { it % 2 == 0 }
}


fun filterPeople(){
    val adultsName = people
        .filter { it.age > 18 }
        .map (Person::name)
    println(adultsName)
}

fun whoIsTheOldest(){
    val maxAge = people.maxBy { it.age }?.age
    val theOldest =
            people.filter { it.age == maxAge }
}

fun filterAdult(){
    people.all(isAdultPredicate)
    people.any(isAdultPredicate)
    people.count(isAdultPredicate)
    val adult: Person? = people.find(isAdultPredicate)
}

fun groupBy(){
    val rsult:Map<Int, List<Person>> = people.groupBy (Person::age)
}

fun mapNumber(){
    numbers.map { it * it }
}

fun containerMap(){
    val numberMap = mapOf("one" to 1, "three" to 3, "ten" to 10)
    numberMap.mapKeys{it.key.toUpperCase()}
}

data class Book(val title: String, val authors: List<String>)

fun flatMap(){
    val alphabetic = listOf("abc", "def", "gh")
    val resultMap: List<List<Char>> = alphabetic.map { it.toList() }
    val transformation: (String) -> Iterable<Char> =  { it.toList() }
    val resultFlatMap: List<Char> = alphabetic.flatMap (transformation)
    alphabetic.flatMap(transformation)
}

fun flatMapBook(){
    val books = listOf(Book("Thursday Next", listOf("Jasper Fo")),
                        Book("Mort", listOf("Terry Pratchett")), Book("Good Omens", listOf("Terry Pratchett","Neal Gaiman")))
    val booksTransformation: (Book) -> Iterable<String> = { book: Book -> book.authors.toList() }
    books.flatMap(booksTransformation).toSet()

}
fun main(args: Array<String>) {
    filterPeople()
}
