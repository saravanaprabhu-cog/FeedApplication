package com.saravana.feedapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.saravana.feedapplication.feedlist.activity.FeedDetailActivity
import com.saravana.feedapplication.feedlist.activity.FeedListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val feedListIntent = Intent(this@MainActivity, FeedListActivity::class.java)
            startActivity(feedListIntent)
        }, 1000)
    }
}
