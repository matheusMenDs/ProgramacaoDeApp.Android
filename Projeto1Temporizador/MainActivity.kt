package com.example.temporizador

import android.app.PendingIntent
import android.content.IntentSender.OnFinished
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.editValor)
        val buttonStart: Button = findViewById(R.id.start)
        val buttonStop: Button = findViewById(R.id.stop)
        val result: TextView = findViewById(R.id.result)

        buttonStart.setOnClickListener {
            try {
                val number = editText.text.toString().toLong()

                timer = object :CountDownTimer(number * 60 * 1000, 1000){
                    override fun onTick(millisUntilFinished: Long) {
                        var seconds = millisUntilFinished /1000
                        var minutes = seconds / 60
                        seconds = seconds %60
                        result.text = String.format("%02d:%02d", minutes, seconds)
                    }

                    override fun onFinish() {
                        result.text = "FINISH!"
                    }

                }

                timer?.start()


            } catch (e: NumberFormatException){
                Toast.makeText(this, "Nenhum número digitado!", Toast.LENGTH_SHORT).show()
            }

            buttonStop.setOnClickListener {
                timer?.cancel()
            }

        }
    }

}