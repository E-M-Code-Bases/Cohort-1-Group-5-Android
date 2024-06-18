package com.dominic.movieswatch

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dominic.movieswatch.ui.HomePage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Assuming `main` is a view inside activity_main.xml
        val main = findViewById<View>(R.id.main)
        main?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        // Set OnClickListener for Watch Now button
        findViewById<Button>(R.id.btn_watch_now)?.setOnClickListener {
            // Navigate to HomePage activity
            val intent = Intent(this, HomePage ::class.java)
            startActivity(intent)
        }
    }
}
