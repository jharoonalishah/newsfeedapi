package com.codetask.alfutaim.dailynewsfeed.app.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.codetask.alfutaim.dailynewsfeed.ApplicationContext
import com.codetask.alfutaim.dailynewsfeed.R
import com.codetask.alfutaim.dailynewsfeed.app.model.Master
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val myToolbar = findViewById<View>(R.id.dtoolbar) as Toolbar
        setSupportActionBar(myToolbar)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        var master: Master = getIntent().getSerializableExtra("Master") as Master


        setUpDetailScreen(master)

    }

    private fun setUpDetailScreen(master: Master) {

        var title: TextView = findViewById(R.id.titleText)
        var published: TextView = findViewById(R.id.publishedText)
        var content: TextView = findViewById(R.id.contentText)
        var image: ImageView = findViewById(R.id.imageView)

        Picasso.with(ApplicationContext.getContext()).load(master.image).into(image)

        title.setText(master.title)
        published.setText(master.published)
        content.setText(master.content)

    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
