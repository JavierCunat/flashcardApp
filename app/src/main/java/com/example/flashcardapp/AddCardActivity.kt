package com.example.flashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView

class AddCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        /* ------------------ Cancel Button Finishing Activity --------------------------- */

        val cancelButton = findViewById<ImageView>(R.id.cancelButtonImage)
        cancelButton.setOnClickListener{
            finish()
        }

        /* ---------- Grab user input and add Save button which passes data between activities -------- */

        val userInputQuestion = findViewById<EditText>(R.id.editTextQuestion)
        val userInputAnswer = findViewById<EditText>(R.id.editTextAnswer)


        val saveButton = findViewById<ImageView>(R.id.saveButtonImage)
        saveButton.setOnClickListener{

            val questionString = userInputQuestion.text.toString()
            val answerString = userInputAnswer.text.toString()

            val data = Intent() // create a new Intent, this is where we will put our data

            data.putExtra("QUESTION_KEY", questionString) // puts one string into the Intent, with the key as 'string1'

            data.putExtra("ANSWER_KEY", answerString) // puts another string into the Intent, with the key as 'string2

            setResult(RESULT_OK, data) // set result code and bundle data for response

            finish()


        }









    }
}