package co.ocha.moviesave

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import butterknife.OnItemClick
import co.ocha.moviesave.Model.Movie
import android.view.View
import android.widget.TextView

class MovieAdapter(
    private val listItem: ArrayList<Movie>,
    private val listener: MovieListener
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val item = listItem[position]
        holder.textViewTitle.text = item.title
        holder.textViewBody.text = item.description
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return listItem.size
    }


    interface MovieListener{
        fun OnItemClicked(movie: Movie)
    }

    class MovieViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var textViewTitle = itemView.findViewById<TextView>(R.id.text_view_title);
        var textViewBody = itemView.findViewById<TextView>(R.id.text_view_body);
    }
}