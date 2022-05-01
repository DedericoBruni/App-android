package com.example.portfolioanalysis

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.*
import com.chaquo.python.android.AndroidPlatform
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PortfolioReturn : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio_return)
        if(! Python.isStarted()){
            Python.start(AndroidPlatform(this));
        }
        val py = Python.getInstance();
        val module = py.getModule("script_android")
        val textView1: TextView = findViewById(R.id.tickersReturn) as TextView
        val textView2: TextView = findViewById(R.id.weightsReturn) as TextView
        val textView3: TextView = findViewById(R.id.yearReturn) as TextView
        val textView4: TextView = findViewById(R.id.monthReturn) as TextView
        val textView5: TextView = findViewById(R.id.dayReturn) as TextView



        val tickers = textView1.text.toString()
        val weights = textView2.text.toString()
        val year = textView3.text.toString()
        val month = textView4.text.toString()
        val day = textView4.text.toString()






        var btnsend = findViewById(R.id.calcolaReturn) as Button
        btnsend.setOnClickListener{
            var job = GlobalScope.launch {
                findViewById<TextView>(R.id.returnResult).text = module.callAttr("portfolio_return", textView1.text.toString(),textView2.text.toString(),textView3.text.toString(),textView4.text.toString(),textView5.text.toString()).toString() ;
                findViewById<TextView>(R.id.loadingReturn).text = ""
            }
            findViewById<TextView>(R.id.loadingReturn).text = "Loading..."






        }
    }
}