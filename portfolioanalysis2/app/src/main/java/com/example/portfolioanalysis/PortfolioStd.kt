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
import kotlinx.coroutines.launch

class PortfolioStd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio_std)

        if(! Python.isStarted()){
            Python.start(AndroidPlatform(this));
        }
        val py = Python.getInstance();
        val module = py.getModule("script_android")
        val textView1: TextView = findViewById(R.id.tickersStd) as TextView
        val textView2: TextView = findViewById(R.id.weightsStd) as TextView
        val textView3: TextView = findViewById(R.id.yearStd) as TextView
        val textView4: TextView = findViewById(R.id.monthStd) as TextView
        val textView5: TextView = findViewById(R.id.dayStd) as TextView


        var btnsend = findViewById(R.id.calcolaStd) as Button
        btnsend.setOnClickListener{
            var job = GlobalScope.launch {
                findViewById<TextView>(R.id.resultStd).text = module.callAttr("portfolio_std", textView1.text.toString(),textView2.text.toString(),textView3.text.toString(),textView4.text.toString(),textView5.text.toString()).toString() ;
                findViewById<TextView>(R.id.loadingStd).text = ""

            }
            findViewById<TextView>(R.id.loadingStd).text = "Loading..."

        }
}
}