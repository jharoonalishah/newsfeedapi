package com.codetask.alfutaim.dailynewsfeed.app.adaptor

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.codetask.alfutaim.dailynewsfeed.R
import com.codetask.alfutaim.dailynewsfeed.app.model.Master
import com.codetask.alfutaim.dailynewsfeed.core.adaptor.NewsFeedBaseAdaptor
import com.squareup.picasso.Picasso
import java.net.URL



class MainPageAdaptor(context : Context, items: List<Master>) : NewsFeedBaseAdaptor(){


    val context= context
    val items = items


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val itemView : View

        itemView = LayoutInflater.from(context).inflate(R.layout.newsfeed_single_layout_adaptor, null)
        val imageView:ImageView = itemView.findViewById(R.id.image)
        val titleView:TextView  = itemView.findViewById(R.id.title)
        val descriptionView:TextView = itemView.findViewById(R.id.description)

        val item = items[position]

        Picasso.with(context).load(item.image).into(imageView)
        titleView.text = item.title
        descriptionView.text = item.description

        return itemView
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return items.size
    }

    fun getBitmapFromUrl(url: String): Bitmap? {

        val url = URL(url)
        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

        return bmp


    }

}