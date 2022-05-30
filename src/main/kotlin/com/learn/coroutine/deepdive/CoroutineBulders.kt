package com.learn.coroutine.deepdive

import kotlinx.coroutines.*

/*
*
* Coroutine builders
* https://kt.academy/article/cc-builders
* */


fun main() {
//    learnAsyncBuilder()
    learnStructureConcurrency()
//    what()
}

private fun learnAsyncBuilder() = runBlocking {
    val messageA = GlobalScope.async {
        delay(1000L)
        "Message A"
    }

    val messageB = GlobalScope.async {
        delay(3000L)
        "Message B"
    }

    val messageC = GlobalScope.async {
        delay(1000L)
        "Message C"
    }
    println(messageA.await())
    println(messageB.await())
    println(messageC.await())
}

private fun learnStructureConcurrency() = runBlocking {
    val parentScope = this
    parentScope.launch {
        delay(4000L)
        launch {
            delay(2000L)
            println("Done!")
        }
        println("Last but Second!")

    }
    parentScope.launch {
        println("Deeper")
    }
    println("Complete?")
}

/*
*
* Structured Concurrency
* */
private fun what() = runBlocking {
    GlobalScope.launch {
        delay(1000L)
        println(" world!")
    }
    println("Hello")
}

private fun where() = runBlocking {
    launch {
        delay(1000L)
        println(" world!")
    }
    println("Hello")
}


/*
*
* Coroutine scope function
* */

class Author(
        val id: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Author

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}


class Article(
        val author: Author
)

class ArticleRepository {

    suspend fun getArticles(): List<Article> {
        delay(1000L)
        return emptyList()
    }
}


class AuthorRepository {

    suspend fun getAuthor(id: String): Author {
        delay(2000L)
        return Author(id)
    }
}


class ArticleByAuthorUseCase(
        private val articleRepository: ArticleRepository,
        private val authorRepository: AuthorRepository
) {

    /*
    * Not parallel
    * */
    suspend fun getArticleByAuthorA(authorId: String): List<Article> {
        val articleList = articleRepository.getArticles()
        val author = authorRepository.getAuthor(authorId)
        return articleList.filter { it.author == author }
    }

    /*
    * Parallel
    * */
    suspend fun getArticleByAuthorB(authorId: String): List<Article> = coroutineScope {
        val articlesDeferred = async { articleRepository.getArticles() }
        val authorDeferred = async { authorRepository.getAuthor(authorId) }
        val articles = articlesDeferred.await()
        val author = authorDeferred.await()
        articles.filter { author == it.author }
    }

    suspend fun getArticleByAuthorC(authorId: String): List<Article> {
        return coroutineScope {
            val articlesDeferred = async { articleRepository.getArticles() }
            val author = authorRepository.getAuthor(authorId)
            articlesDeferred.await().filter { it.author == author }
        }

    }
}

