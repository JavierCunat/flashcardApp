package com.example.flashcardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* ------------------- Question and Answer Visibility Function ------------------- */
        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)
        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE

        }
        /* --------------------- Answer Choice Background Functions ---------------------- */

        val answerChoiceOne = findViewById<View>(R.id.answerchoice_one)
        answerChoiceOne.setOnClickListener {
            answerChoiceOne.setBackgroundColor(resources.getColor(R.color.my_red_color, null))
        }

        val answerChoiceTwo = findViewById<View>(R.id.answerchoice_two)
        answerChoiceTwo.setOnClickListener {
            answerChoiceTwo.setBackgroundColor(resources.getColor(R.color.my_red_color, null))
        }

        val answerChoiceThree = findViewById<View>(R.id.answerchoice_three)
        answerChoiceThree.setOnClickListener {
            answerChoiceThree.setBackgroundColor(resources.getColor(R.color.my_red_color, null))
        }

        val answerChoiceFour = findViewById<View>(R.id.answerchoice_four)
        answerChoiceFour.setOnClickListener {
            answerChoiceFour.setBackgroundColor(resources.getColor(R.color.green, null))
        }
        /* ------------ Answer Choices Visibility when clicking eye button Function ----------- */


        val toggleChoicesVisibility = findViewById<ImageView>(R.id.toggle_choices_visibility)
        toggleChoicesVisibility.setOnClickListener {
            toggleChoicesVisibility.setImageResource(R.drawable.eyeclosed_foreground)
            answerChoiceOne.visibility = View.INVISIBLE
            answerChoiceTwo.visibility = View.INVISIBLE
            answerChoiceThree.visibility = View.INVISIBLE
            answerChoiceFour.visibility = View.INVISIBLE


        }

    }
}

