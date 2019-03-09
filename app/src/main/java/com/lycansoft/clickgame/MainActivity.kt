package com.lycansoft.clickgame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var score:Int=0
    var imageArray=ArrayList<ImageView>()
    var handler:Handler=Handler()
    var runnable:Runnable= Runnable {  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        score = 0

        imageArray= arrayListOf(iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9)
        hideImage()


        object : CountDownTimer(10000, 1000) {
            override fun onFinish() {
                txvTimer.text = "Time Out"
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility=View.INVISIBLE
                }

            }

            override fun onTick(millisUntilFinished: Long) {
                txvTimer.text = "${millisUntilFinished / 1000}"
            }


        }.start()

    }


    fun hideImage() {

        runnable=object :Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility=View.INVISIBLE
                }

                val random= java.util.Random()
                val index=random.nextInt(8-0)
                imageArray[index].visibility=View.VISIBLE

                handler.postDelayed(runnable,600)
            }

        }
        handler.post(runnable)

    }

    fun increaseScore(view: View){
        score++
        txvScore.text="Score: "+score
    }
}
