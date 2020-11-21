package co.ocha.moviesave

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.ocha.moviesave.Model.Movie
import co.ocha.moviesave.Model.TVShow

class TVShowAdapter(
    private val listItem: ArrayList<TVShow>,
    private val listener: TVShowListener
): RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TVShowViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view,parent,false)
        return TVShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
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

    interface TVShowListener{
        fun OnItemClicked(tvShow: TVShow)
    }

    class TVShowViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var textViewTitle = itemView.findViewById<TextView>(R.id.text_view_title);
        var textViewBody = itemView.findViewById<TextView>(R.id.text_view_body);
    }

}