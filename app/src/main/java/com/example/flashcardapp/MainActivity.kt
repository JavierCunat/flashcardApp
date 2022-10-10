package com.example.flashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.view.View
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    /* ------------------------ Adding Database to FlashcardApp ------------------------- */
    lateinit var flashcardDatabase: FlashcardDatabase
    var allFlashcards = mutableListOf<Flashcard>()

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
             flashcardAnswer.setOnClickListener {
                    flashcardQuestion.visibility = View.VISIBLE
                    flashcardAnswer.visibility = View.INVISIBLE
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

        var shouldShowAnswers = true

        val toggleChoicesVisibility = findViewById<ImageView>(R.id.toggle_choices_visibility)
        toggleChoicesVisibility.setOnClickListener {
            if(shouldShowAnswers) {
                toggleChoicesVisibility.setImageResource(R.drawable.eyeclosed_foreground)
                answerChoiceOne.visibility = View.INVISIBLE
                answerChoiceTwo.visibility = View.INVISIBLE
                answerChoiceThree.visibility = View.INVISIBLE
                answerChoiceFour.visibility = View.INVISIBLE
                shouldShowAnswers = false
            }
            else {
                toggleChoicesVisibility.setImageResource(R.drawable.eyeopen_foreground)
                answerChoiceOne.visibility = View.VISIBLE
                answerChoiceTwo.visibility = View.VISIBLE
                answerChoiceThree.visibility = View.VISIBLE
                answerChoiceFour.visibility = View.VISIBLE
                shouldShowAnswers = true

            }


        }
        /* ----------- Switching from First Activity to Second Activity ------------------ */


        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // This code is executed in StartingActivity after we come back from EndingActivity
            // This extracts any data that was passed back from EndingActivity
            val data: Intent? = result.data
            if(data != null){


                val questionString = data.getStringExtra("QUESTION_KEY")
                val answerString = data.getStringExtra("ANSWER_KEY")

                flashcardQuestion.text = questionString
                flashcardAnswer.text = answerString

                if (questionString != null && answerString != null) {
                    flashcardDatabase.insertCard(Flashcard(questionString, answerString))
                    // Update set of flashcards to include new card
                    allFlashcards = flashcardDatabase.getAllCards().toMutableList()
                } else {
                    Log.e("TAG", "Missing question or answer to input into database. Question is $questionString and answer is $answerString")
                }


                Log.i("MainActivity", "Question: $questionString")
                Log.i("MainActivity", "Answer: $answerString")
            } else {
                Log.i("MainActivity", "Returned null data from AddCardActivity")
            }

        }


      val addButton = findViewById<View>(R.id.addButtonImage)
          addButton.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            // Launch EndingActivity with the resultLauncher so we can execute more code
            // once we come back here from EndingActivity
            resultLauncher.launch(intent)
        }


        flashcardDatabase = FlashcardDatabase(this)
        allFlashcards = flashcardDatabase.getAllCards().toMutableList()

        if (allFlashcards.size > 0) {
            findViewById<TextView>(R.id.flashcard_question).text = allFlashcards[0].question
            findViewById<TextView>(R.id.flashcard_answer).text = allFlashcards[0].answer
        }


        /* ----------------- Adding allowing user to browse through multiple cards ------------- */

        var currentCardDisplayedIndex = 0
        val nextButton = findViewById<ImageView>(R.id.nextButton)
        nextButton.setOnClickListener{
            if (allFlashcards.size == 0) {
                // return here, so that the rest of the code in this onClickListener doesn't execute
               return@setOnClickListener
        }
            currentCardDisplayedIndex++

            if(currentCardDisplayedIndex >= allFlashcards.size) {
                currentCardDisplayedIndex = 0
            }

            allFlashcards = flashcardDatabase.getAllCards().toMutableList()

            val question = allFlashcards[currentCardDisplayedIndex].question
            val answer = allFlashcards[currentCardDisplayedIndex].answer

            flashcardQuestion.text = question
            flashcardAnswer.text = answer




            }



    }






}

