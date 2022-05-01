package com.example.portfolioanalysis

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*
import java.util.concurrent.TimeUnit


open class MainActivity: AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var btnreturn = findViewById(R.id.buttonReturn) as Button
        btnreturn.setOnClickListener{
            val intent = Intent(this, PortfolioReturn::class.java)
            startActivity(intent)
        }
        var btnstd = findViewById(R.id.buttonStd) as Button
        btnstd.setOnClickListener{
            val intent = Intent(this, PortfolioStd::class.java)
            startActivity(intent)
        }
        var btnalloc = findViewById(R.id.buttonAlloc) as Button
        btnalloc.setOnClickListener{
            val intent = Intent(this, PortfolioAlloc::class.java)
            startActivity(intent)
        }
        var btncorr = findViewById(R.id.buttonCorr) as Button
        btncorr.setOnClickListener{
            val intent = Intent(this, PortfolioCorr::class.java)
            startActivity(intent)
        }
    }
}

