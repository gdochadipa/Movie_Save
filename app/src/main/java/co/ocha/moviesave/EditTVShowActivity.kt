package co.ocha.moviesave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import co.ocha.moviesave.Model.Movie
import co.ocha.moviesave.Model.TVShow
import co.ocha.moviesave.db.MovieDao
import co.ocha.moviesave.db.MovieRoomDatabase
import kotlinx.android.synthetic.main.activity_edit_t_v_show.*

class EditTVShowActivity : AppCompatActivity() {
    val EDIT_TV_EXTRA = "edit_tv_extra"
    private lateinit var tvShow: TVShow
    private var isUpdate = false
    private lateinit var database: MovieRoomDatabase
    private lateinit var dao: MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_t_v_show)

        database = MovieRoomDatabase.getDatabase(applicationContext)
        dao = database.getMovieDao()

        if (intent.getParcelableExtra<TVShow>(EDIT_TV_EXTRA) != null){
            button_delete.visibility = View.VISIBLE
            isUpdate = true
            tvShow = intent.getParcelableExtra(EDIT_TV_EXTRA)
            edit_text_title.setText(tvShow.title)
            edit_text_body.setText(tvShow.description)

            edit_text_title.setSelection(tvShow.title!!.length)

        }

        button_save.setOnClickListener {
            val title = edit_text_title.text.toString()
            val description = edit_text_body.text.toString()

            if(title.isEmpty() && description.isEmpty()){
                Toast.makeText(applicationContext, "Text cannot be empty", Toast.LENGTH_SHORT).show()
            }else{
                if(isUpdate){
                    saveTV(TVShow(id=tvShow.id,title=title,description=description))
                }else{
                    saveTV(TVShow(title=title,description=description))
                }
            }

            finish()
        }

        button_delete.setOnClickListener {
            deleteTV(tvShow)
            finish()
        }
    }

    private  fun saveTV(tvShow: TVShow){
        if(dao.getById(tvShow.id).isEmpty()){
            dao.insertTV(tvShow)
        }else{
            dao.updateTV(tvShow)
        }
        Toast.makeText(applicationContext, "TV saved", Toast.LENGTH_SHORT).show()
    }

    private fun deleteTV(tvShow: TVShow){
        dao.deleteTV(tvShow)
        Toast.makeText(applicationContext, "TV deleted", Toast.LENGTH_SHORT).show()
    }

}