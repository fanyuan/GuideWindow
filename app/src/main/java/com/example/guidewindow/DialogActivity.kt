package com.example.guidewindow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        lifecycle
    }

    fun dialog(view: View) {finish()}
}