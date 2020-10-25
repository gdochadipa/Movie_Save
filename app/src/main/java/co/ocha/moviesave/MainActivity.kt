package co.ocha.moviesave

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import co.ocha.moviesave.Model.Movie
import co.ocha.moviesave.db.MovieRoomDatabase
import kotlinx.android.synthetic.main.content_main.*
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        getMoviesData()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            startActivity(Intent(this, EditActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun getMoviesData(){
        val database = MovieRoomDatabase.getDatabase(applicationContext)
        val dao = database.getMovieDao()
        val listItem = arrayListOf<Movie>()

        listItem.addAll(dao.getAll())
        setupRecycleView(listItem)
        if(listItem.isNotEmpty()){
            text_view_note_empty.visibility = View.GONE
        }else{
            text_view_note_empty.visibility = View.VISIBLE
        }

    }

    private fun setupRecycleView(listItem: ArrayList<Movie>){
        recycler_view_main.apply {
            adapter = MovieAdapter(listItem, object : MovieAdapter.MovieListener{
                override fun OnItemClicked(movie: Movie) {
                    val intent = Intent(this@MainActivity, EditActivity::class.java)
                    intent.putExtra(EditActivity().EDIT_Movie_EXTRA,movie)
                    startActivity(intent)
                }

            })

            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        getMoviesData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}