package co.ocha.moviesave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Dao
import androidx.room.RoomDatabase
import co.ocha.moviesave.Model.Movie
import co.ocha.moviesave.db.MovieDao
import co.ocha.moviesave.db.MovieRoomDatabase
import kotlinx.android.synthetic.main.activity_edit.*
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class EditActivity : AppCompatActivity() {
    val EDIT_Movie_EXTRA = "edit_movie_extra"
    private lateinit var movie: Movie
    private var isUpdate = false
    private lateinit var database:MovieRoomDatabase
    private lateinit var dao:MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        database = MovieRoomDatabase.getDatabase(applicationContext)
        dao = database.getMovieDao()

        if (intent.getParcelableExtra<Movie>(EDIT_Movie_EXTRA) != null){
            button_delete.visibility = View.VISIBLE
            isUpdate = true
            movie = intent.getParcelableExtra(EDIT_Movie_EXTRA)
            edit_text_title.setText(movie.title)
            edit_text_body.setText(movie.description)

            edit_text_title.setSelection(movie.title!!.length)

        }

        button_save.setOnClickListener {
            val title = edit_text_title.text.toString()
            val description = edit_text_body.text.toString()

            if(title.isEmpty() && description.isEmpty()){
                Toast.makeText(applicationContext, "Text cannot be empty", Toast.LENGTH_SHORT).show()
            }else{
                if(isUpdate){
                    saveMovie( Movie(id = movie.id,title = title, description= description))
                }else{
                    saveMovie(Movie(title = title, description= description))
                }
            }
            finish()
        }

        button_delete.setOnClickListener {
            deleteMovie(movie)
            finish()
        }
    }


    private fun saveMovie(movie: Movie){
        if (dao.getById(movie.id).isEmpty()){
            dao.insert(movie)
        }else{
            dao.update(movie)
        }

        Toast.makeText(applicationContext, "Movie saved", Toast.LENGTH_SHORT).show()
    }

    private fun deleteMovie(movie: Movie){
        dao.delete(movie)
        Toast.makeText(applicationContext, "Movie deleted", Toast.LENGTH_SHORT).show()
    }


}