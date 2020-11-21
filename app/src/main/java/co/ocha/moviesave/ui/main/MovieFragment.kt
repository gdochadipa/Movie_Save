package co.ocha.moviesave.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.ocha.moviesave.EditActivity
import co.ocha.moviesave.EditTVShowActivity
import co.ocha.moviesave.Model.Movie
import co.ocha.moviesave.MovieAdapter
import co.ocha.moviesave.R
import co.ocha.moviesave.db.MovieRoomDatabase
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_new_main.*
import kotlinx.android.synthetic.main.fragment_new_main.text_view_note_empty
import java.io.Console

/**
 * A placeholder fragment containing a simple view.
 */

private const val ARG_CONTEXT = "param1"

class MovieFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var layoutManager:RecyclerView.LayoutManager?=null
//    private lateinit var recycle_view:RecyclerView
//    private lateinit var text_view_note_empty:TextView
//    private lateinit var addMovieBtn: Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_new_main, container, false)
//        recycle_view = root.findViewById(R.id.recycler_view_main_Movie)
//        text_view_note_empty = root.findViewById(R.id.text_view_note_empty)
//        addMovieBtn = root.findViewById(R.id.addMovieBtn)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMovieBtn.setOnClickListener {
            startActivity(Intent(activity, EditActivity::class.java))
        }
        getData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
//            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
//        }

    }

    private fun getData(){

        val database = MovieRoomDatabase.getDatabase(requireContext())
        val dao = database.getMovieDao()
        val listItem = arrayListOf<Movie>()

        listItem.addAll(dao.getAll())
        setUpRecycleView(listItem)
        if(listItem.isNotEmpty()){
            text_view_note_empty.visibility = View.GONE
        }else{

            text_view_note_empty.visibility = View.VISIBLE
        }
    }


    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun setUpRecycleView(listItem: ArrayList<Movie>){
        recycler_view_main_Movie.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter= MovieAdapter(listItem,object : MovieAdapter.MovieListener{
                override fun OnItemClicked(movie: Movie) {
                    val intent = Intent(activity, EditActivity::class.java)
                    intent.putExtra(EditActivity().EDIT_Movie_EXTRA,movie)
                    startActivity(intent)
                }

            })
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): MovieFragment {
            return MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)

                }
            }
        }
    }
}