package co.ocha.moviesave.db

import androidx.room.*
import co.ocha.moviesave.Model.Movie
import co.ocha.moviesave.Model.TVShow

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

    @Insert
    fun insertTV(tvShow: TVShow)

    @Update
    fun updateTV(tvShow: TVShow)

    @Delete
    fun deleteTV(tvShow: TVShow)

    @Query("select * from tv_show")
    fun getAllTV(): List<TVShow>

    @Query("SELECT * FROM tv_show WHERE id = :id")
    fun getTVById(id: Int) : List<TVShow>

}
