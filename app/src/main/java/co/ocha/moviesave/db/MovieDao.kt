package co.ocha.moviesave.db

import androidx.room.*
import co.ocha.moviesave.Model.Movie

@Dao
interface MovieDao {

    @Insert
    fun insert(movie: Movie)

    @Update
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("Select * from movies")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getById(id: Int) : List<Movie>

}
