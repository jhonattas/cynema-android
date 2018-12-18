package br.com.patrocine.patrocine.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.patrocine.patrocine.R
import br.com.patrocine.patrocine.model.Movie
import br.com.patrocine.patrocine.ui.interfaces.OnFragmentInteractionListener
import com.squareup.picasso.Picasso
import java.util.ArrayList

class MovieAdapter(var context: Context, private val movieList: ArrayList<Movie>, private val listener: OnFragmentInteractionListener?) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movie = movieList[position]
        holder.name.text = holder.movie!!.fullTitle
        Picasso.with(context).load(holder.movie!!.image).into(holder.thumbnail)

        holder.cardView.setOnClickListener {
            listener?.onFragmentInteraction(holder.movie!!)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MovieViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {
        var movie: Movie? = null
        val name: TextView = mView.findViewById(R.id.title)
        val thumbnail: ImageView = mView.findViewById(R.id.thumbnail)
        val cardView: CardView = mView.findViewById(R.id.card_view)

    }
}