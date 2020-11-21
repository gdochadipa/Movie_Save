package co.ocha.moviesave.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.ocha.moviesave.EditActivity
import co.ocha.moviesave.EditTVShowActivity
import co.ocha.moviesave.Model.Movie
import co.ocha.moviesave.Model.TVShow
import co.ocha.moviesave.R
import co.ocha.moviesave.TVShowAdapter
import co.ocha.moviesave.db.MovieRoomDatabase
import kotlinx.android.synthetic.main.fragment_genre.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GenreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TVShowFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var layoutManager:RecyclerView.LayoutManager?=null
//    private lateinit var recycle_view:RecyclerView
//    private lateinit var text_view_note_empty:TextView
//    private lateinit var addTVBtn:Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root  = inflater.inflate(R.layout.fragment_genre, container, false)
//        recycle_view = root.findViewById(R.id.recycler_view_main)
//        text_view_note_empty= root.findViewById(R.id.text_view_note_empty)
//        addTVBtn= root.findViewById(R.id.addTVBtn)
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTVData()

        addTVBtn.setOnClickListener {
            startActivity(Intent(activity, EditTVShowActivity::class.java))
        }
    }

    private fun getTVData(){
        val database = MovieRoomDatabase.getDatabase(requireContext())
        val dao = database.getMovieDao()
        val listItem = arrayListOf<TVShow>()
        listItem.addAll(dao.getAllTV())
        setupRecycleView(listItem)
        if(listItem.isNotEmpty()){
            text_view_note_empty.visibility = View.GONE
        }else{
            text_view_note_empty.visibility = View.VISIBLE
        }


    }

    private fun setupRecycleView(listItem:ArrayList<TVShow>){
        recycler_view_main.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TVShowAdapter(listItem, object :TVShowAdapter.TVShowListener{
                override fun OnItemClicked(tvShow: TVShow) {
                    val intent = Intent(activity, EditTVShowActivity::class.java)
                    intent.putExtra(EditTVShowActivity().EDIT_TV_EXTRA,tvShow)
                    startActivity(intent)
                }

            })
        }
    }

    override fun onResume() {
        super.onResume()
        getTVData()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GenreFragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(sectionNumber: Int) =
            TVShowFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
    }
}