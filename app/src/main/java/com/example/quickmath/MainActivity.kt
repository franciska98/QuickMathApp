package com.example.quickmath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var correctAnswers:Int=0
    var totalAnswers:Int=0
    var resultOfEquation:Int=0
    var positionOfCorrectAnswer:Int=0
    var answers= mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun play(view:View){
        startButton.visibility = View.INVISIBLE
        correctWrongTextView.visibility=View.INVISIBLE
        playAgainButton.visibility=View.INVISIBLE
        answerButton1.isEnabled = true
        answerButton2.isEnabled = true
        answerButton3.isEnabled = true
        answerButton4.isEnabled = true

        correctAnswers=0
        totalAnswers=0
        resultTextView.text="$correctAnswers/$totalAnswers"

        object:CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                var restOfTime:Long= millisUntilFinished/1000
                timerTextView.text="$restOfTime s"
            }

            override fun onFinish() {
                correctWrongTextView.text="Done"
                playAgainButton.visibility=View.VISIBLE
                answerButton1.isEnabled = false
                answerButton2.isEnabled = false
                answerButton3.isEnabled = false
                answerButton4.isEnabled = false
            }

        }.start()

        setGame()
    }

    fun randomNumber(low:Int,high:Int):Int{
        return (low..high).random()
    }

    fun setGame(){
        var firstNumber:Int=randomNumber(1,20)
        var secondNumber:Int=randomNumber(1,20)
        equationTextView.text="$firstNumber+$secondNumber"

        resultOfEquation=firstNumber+secondNumber
        positionOfCorrectAnswer=randomNumber(1,4)
        answers.clear()
        for (i in 1..4){
            if(i==positionOfCorrectAnswer) answers.add(resultOfEquation)
            else{
                var wrongAnswer=randomNumber(1,40)
                while (wrongAnswer==resultOfEquation){
                    wrongAnswer=randomNumber(1,40)
                }
                answers.add(wrongAnswer)
            }
        }
        answerButton1.text="${answers[0]}"
        answerButton2.text="${answers[1]}"
        answerButton3.text="${answers[2]}"
        answerButton4.text="${answers[3]}"
    }

    fun chooseAnswer(view:View){
        correctWrongTextView.visibility=View.VISIBLE
        val positionOfChoosenAnswer = view.tag.toString().toInt()
        if(positionOfChoosenAnswer==positionOfCorrectAnswer){
            correctWrongTextView.text="Correct"
            correctAnswers++
        }
        else correctWrongTextView.text="Wrong"
        totalAnswers++
        resultTextView.text="$correctAnswers/$totalAnswers"
        setGame()
    }
}