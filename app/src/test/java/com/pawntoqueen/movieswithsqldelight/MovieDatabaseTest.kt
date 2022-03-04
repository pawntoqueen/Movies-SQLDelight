package com.pawntoqueen.movieswithsqldelight

import com.pawntoqueen.sqldelight.models.db.Movies
import com.raywenderlich.android.sqldelight.db.Database
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MovieDatabaseTest {

    private val inMemorySqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
        Database.Schema.create(this)
    }

    private val queries = Database(inMemorySqlDriver).moviesQueries

    @Test
    fun smokeTest() {
        val emptyItems: List<Movies> = queries.select_all().executeAsList()
        assertEquals(emptyItems.size, 0)

        queries.add_movie(
            movie_name = "Fountain",
            movie_imdb_point = 7,
            release_year = 2006
        )

        val items: List<Movies> = queries.select_all().executeAsList()
        assertEquals(items.size, 1)

        val movie = queries.select_by_name("Fountain").executeAsOneOrNull()
        assertEquals(movie?.movie_imdb_point?.toInt(), 7)
        assertEquals(movie?.release_year?.toInt(), 2006)
    }
}