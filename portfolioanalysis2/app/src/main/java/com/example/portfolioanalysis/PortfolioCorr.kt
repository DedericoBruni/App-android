package com.example.portfolioanalysis

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PortfolioCorr: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio_corr)
        if(! Python.isStarted()){
            Python.start(AndroidPlatform(this));
        }
        val py = Python.getInstance();
        val module = py.getModule("script_android")
        val textView1: TextView = findViewById(R.id.tickersCorr) as TextView
        val textView2: TextView = findViewById(R.id.yearCorr) as TextView
        val textView3: TextView = findViewById(R.id.monthCorr) as TextView
        val textView4: TextView = findViewById(R.id.dayCorr) as TextView
        val textView5: TextView = findViewById(R.id.loadingCorr) as TextView

        var btnsend = findViewById(R.id.calcolaCorr) as Button
        btnsend.setOnClickListener{

            var job = GlobalScope.launch {
                findViewById<TextView>(R.id.resultCorr).text = module.callAttr("portfolio_corr", textView1.text.toString(),textView2.text.toString(),textView3.text.toString(),textView4.text.toString()).toString();
                findViewById<TextView>(R.id.loadingCorr).text = ""

            }
            findViewById<TextView>(R.id.loadingCorr).text = "Loading..."




        }

    }
}