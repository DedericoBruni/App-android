package com.example.portfolioanalysis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PortfolioAlloc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio_alloc)
        if(! Python.isStarted()){
            Python.start(AndroidPlatform(this));
        }
        val py = Python.getInstance();
        val module = py.getModule("script_android")
        val textView1: TextView = findViewById(R.id.tickersAlloc) as TextView
        val textView2: TextView = findViewById(R.id.yearAlloc) as TextView
        val textView3: TextView = findViewById(R.id.monthAlloc) as TextView
        val textView4: TextView = findViewById(R.id.dayAlloc) as TextView
        val textView5: TextView = findViewById(R.id.loading) as TextView



        var btnsend = findViewById(R.id.calcolaAlloc) as Button
        btnsend.setOnClickListener{

            var job = GlobalScope.launch {
                findViewById<TextView>(R.id.resultAlloc).text = module.callAttr("find_best_allocation", textView1.text.toString(),textView2.text.toString(),textView3.text.toString(),textView4.text.toString()).toString();
                findViewById<TextView>(R.id.loading).text = ""

            }
            findViewById<TextView>(R.id.loading).text = "Loading..."




        }

    }
}