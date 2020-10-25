package co.ocha.moviesave.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.ocha.moviesave.Model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false )
abstract class MovieRoomDatabase : RoomDatabase(){


    companion object{
        @Volatile
        private var INSTANCE:MovieRoomDatabase? = null

        fun getDatabase(context: Context): MovieRoomDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "movie_db"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }

        }
    }

    abstract fun getMovieDao():MovieDao
}