package com.example.guidewindow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class GuideMultipleViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_multiple_view)
    }

    fun topBaseView(view: View) {}
    fun leftBaseView(view: View) {}
    fun rightBaseView(view: View) {}
    fun bottomBaseView(view: View) {}
}