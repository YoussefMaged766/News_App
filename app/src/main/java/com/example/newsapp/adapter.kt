package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.api.ArticalResponse
import com.example.newsapp.api.ArticlesItem


class adapter : RecyclerView.Adapter<adapter.viewholder>() {
    var sourse: List<ArticlesItem>? = null

    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txttitle: TextView = itemView.findViewById(R.id.txttitle)
        var txtdescription: TextView = itemView.findViewById(R.id.txtdescription)
        var txtpublish: TextView = itemView.findViewById(R.id.txtpublish)
        var imgarticle: ImageView = itemView.findViewById(R.id.imgarticle)
        var txtauther: TextView = itemView.findViewById(R.id.txtauther)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.artical_item, parent, false)
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var items = sourse?.get(position)
        holder.txttitle.text = items?.title
        holder.txtauther.text = items?.author
        holder.txtdescription.text = items?.description
        holder.txtpublish.text = items?.publishedAt
        Glide.with(holder.itemView).load(items?.urlToImage).into(holder.imgarticle)
    }

    override fun getItemCount(): Int {
        return sourse?.size ?: 0
    }

    fun getdata(data :List<ArticlesItem>){
        sourse=data
        notifyDataSetChanged()

    }
}