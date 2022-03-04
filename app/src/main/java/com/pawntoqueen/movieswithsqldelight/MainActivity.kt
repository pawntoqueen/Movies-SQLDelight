package com.pawntoqueen.movieswithsqldelight
import com.squareup.sqldelight.android.AndroidSqliteDriver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pawntoqueen.android.sqldelight.db.Database
import com.pawntoqueen.sqldelight.models.db.Movies

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         val androidSqlDriver =  AndroidSqliteDriver(
            schema = Database.Schema,
            context = applicationContext,
            name = "items.db"
        )

        val queries = Database(androidSqlDriver).moviesQueries

        val itemsBefore: List<Movies> = queries.select_all().executeAsList()
        Log.d("ItemDatabase", "Items Before: $itemsBefore")

        queries.add_movie("Spiderman", 8, 2002)
        queries.add_movie("Ironman",8 , 2008)
        queries.add_movie("Captain America", 7, 2011)



        val itemsAfter: List<Movies> = queries.select_all().executeAsList()
        Log.d("ItemDatabase", "Items After: $itemsAfter")


    }
}

