package dev.matyaqubov.networking.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.matyaqubov.networking.MainActivity
import dev.matyaqubov.networking.Poster
import dev.matyaqubov.networking.R

class PosterAdapter (var activity: MainActivity, var items:ArrayList<Poster>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poster_list,parent,false)
        return PosterViewHolder(view)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val poster = items[position]

        if (holder is PosterViewHolder) {
            val tv_title = holder.tv_title
            val tv_body = holder.tv_body

            tv_title.text = poster.title
            tv_body.text = poster.body
            holder.ll_poster.setOnClickListener {
                activity.dialogPoster(poster)
                false
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PosterViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tv_title: TextView
        var tv_body:TextView
        var ll_poster:LinearLayout

        init {
            tv_title = view.findViewById(R.id.tv_title)
            tv_body = view.findViewById(R.id.tv_body)
            ll_poster=view.findViewById(R.id.ll_poster)
        }
    }
}